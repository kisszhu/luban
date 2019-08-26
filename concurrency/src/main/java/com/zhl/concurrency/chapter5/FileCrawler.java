package com.zhl.concurrency.chapter5;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 桌面搜索.
 * 即在某个文件层次结构中搜索符合索引标准的文件，并将它们的名称放入工作队列
 * 而且，在Indexer中还给出了一个消费者任务，即从队列中取出文件名称并对他们建立索引
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> queue, FileFilter filter, File root) {
        this.fileQueue = queue;
        this.fileFilter = filter;
        this.root = root;
    }

    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else {
                    System.out.println("生产者：" + entry.getName());
                    fileQueue.put(entry);
                }
                // else if (!alreadyIndexed(entry)) {
                //     fileQueue.put(entry);
                // }
            }
        }
    }

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<File>(100);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }

    public static void main(String[] args) {
        File[] files = new File[2];
        files[0] = new File("E:\\ppt");
        files[1] = new File("E:\\ppt");
        startIndexing(files);
    }
}

class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // indexFile(queue.take());
                File file = queue.take();
                System.out.println("消费者：" + file.getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
