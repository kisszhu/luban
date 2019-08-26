package com.zhl.concurrency.chapter6;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用CompletionService.
 * 如果向Executor提交了一组计算任务，并且希望在计算完成后获得结果，那么可以保留与每个任务关联的Future，然后反复使用
 * get方法，同时将参数timeout指定为0，从而通过轮询来判断任务是否完成。这种方法虽然可行，但却有些繁琐。幸运的是，还有一种
 * 更好的方法：完成服务（CompletionService）
 */
public class Renderer {
    /**
     * CompletionService将Executor和BlockingQueue的功能融合在一起，你可以将Callable任务提交给它执行，然后
     * 使用类似于队列操作的task和poll等方法来获得已完成的结果，而这些结果在完成时将被封装为Future。
     */
    private final ExecutorService executor;
    private final long TIME_BUDGET = 10;

    Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);

        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }

        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                /**
                 * ExecutorCompletionService的实现非常简单。在构造函数中创建一个BlockingQueue来保存计算完成的结果。
                 * 当计算完成时，调用Future-Task中的done方法。当提交某个任务时，该任务将首先包装为一个QueueingFuture，
                 * 这是FutureTask的一个子类，然后在改写子类的done方法，并将结果放入BlocikingQueue中。task和poll方法委托
                 * 给了BlockingQueue，这些方法会在得出结果之前阻塞。
                 */
                Future<ImageData> f = completionService.take();
                long endNanos = System.nanoTime() + 10;
                long timeLift = endNanos - System.nanoTime();
                ImageData imageData = f.get(timeLift, TimeUnit.NANOSECONDS);
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            // future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    /**
     * 如果Throwable是Error，那么抛出它，如果是RuntimeException，那么返回它，否则抛出
     * IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else
            throw new IllegalStateException("Not unchecked", t);
    }

    void renderText(CharSequence source) {

    }

    void renderImage(ImageData data) {

    }

    public List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}
