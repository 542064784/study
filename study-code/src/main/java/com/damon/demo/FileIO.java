package com.damon.demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件读写
 *
 * @author Damon Chen
 * @date 2019/03/03
 */
public class FileIO {

    private static final String IN_FILE_PATH = "src/main/java/com/damon/demo/FileIO.java";
    private static final String OUT_FILE_PATH = "src/main/resource/fileIO.txt";

    /**
     * 文件读写 一次读取一个字节
     *
     * @throws FileNotFoundException
     */
    private void fileByByte() throws IOException {
        InputStream inputStream = new FileInputStream(IN_FILE_PATH);
        OutputStream outputStream = new FileOutputStream(OUT_FILE_PATH);
        int b;
        while ((b = inputStream.read()) != -1) {
            outputStream.write(b);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
    }

    /**
     * 读写文件一次读取一个字节数组
     *
     * @throws IOException
     */
    private void fileByByteArray() throws IOException {
        InputStream inputStream = new FileInputStream(IN_FILE_PATH);
        //  可以使用字节缓冲流包装，可以更快的读写
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        OutputStream outputStream = new FileOutputStream(OUT_FILE_PATH);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] byteArray = new byte[1024];
        int len;
        while ((len = inputStream.read(byteArray)) != -1) {
            outputStream.write(byteArray, 0, len);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();
    }

    /**
     * 读写文件 一次读取一个字符
     *
     * @throws IOException
     */
    private void fileByChar() throws IOException {
        FileReader fileReader = new FileReader(IN_FILE_PATH);
        FileWriter fileWriter = new FileWriter(OUT_FILE_PATH);
        int ch;
        while ((ch = fileReader.read()) != -1) {
            fileWriter.write(ch);
            fileWriter.flush();
        }
        fileWriter.close();
        fileReader.close();
    }

    /**
     * 读些文件 一次读取一个字符数组
     *
     * @throws IOException
     */
    private void fileByCharArray() throws IOException {
        FileReader fileReader = new FileReader(IN_FILE_PATH);
        FileWriter fileWriter = new FileWriter(OUT_FILE_PATH);
        char[] charArray = new char[1024];
        int len;
        while ((len = fileReader.read(charArray)) != -1) {
            fileWriter.write(charArray, 0, len);
            fileWriter.flush();
        }
        fileWriter.close();
        fileReader.close();
    }

    /**
     * 读写文件 一次读取一行
     *
     * @throws IOException
     */
    private void fileByString() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(IN_FILE_PATH));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUT_FILE_PATH));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    /**
     * 递归读取文件夹并压缩成zip流
     *
     * @param zipOutputStream zipOutPutStream
     * @param file            file
     * @param count           文件在文件夹的层数
     */
    public void recursiveFolderByZip(ZipOutputStream zipOutputStream, File file, Integer count) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                count++;
                recursiveFolderByZip(zipOutputStream, f, count);
                count--;
            }
        } else {
            String fileName = "";
            for (int i = 0; i < count; i++) {
               fileName =  fileName.concat(getFile(file, count-i).getName()).concat(File.separator);
            }
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes)) != -1) {
                zipOutputStream.write(bytes, 0, len);
            }
            in.close();
        }
    }

    // 获得父File对象
    private File getFile(File file, Integer count) {
        if (count == 1) {
            return file;
        }
        return getFile(file.getParentFile(), --count);
    }


    public  void testZip() throws IOException {
        File file = new File("src/main/java");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("src/main/resource/testZip.zip"));
        Integer count = 0;
        recursiveFolderByZip(zipOutputStream,file,count);
        zipOutputStream.close();
        // zip解压缩 直接使用 ZipInputStream 读取就好了，会直接根据压缩包中的目录自动创建
    }

}
