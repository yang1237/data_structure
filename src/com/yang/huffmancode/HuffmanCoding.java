package com.yang.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼压缩实现
 * 1.通过赫夫曼编码实现出现次数较多的字符，其路径较短
 * 2.其路上上的每一步用一个0或1代替
 * 由0或1组成的路径就表示了该字符
 */
public class HuffmanCoding {

    static HashMap<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//
//        byte[] bytes = content.getBytes();
//        byte[] zip = zip(bytes);
//        byte[] bytes1 = deCode(huffmanCodes, zip);
//        System.out.println(new String(bytes1));

        String srcFile = "/Users/yangjianfeng/tes.zip";
        String destFile = "/Users/yangjianfeng/hello.jpg";

        unzipFile(srcFile, destFile);

        System.out.println("OK!");
    }

    private static void unzipFile(String zipFile,String destFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is=new FileInputStream(zipFile);
            ois=new ObjectInputStream(is);
            HashMap<Byte, String> map = (HashMap<Byte, String>) ois.readObject();
            byte[] bytes = (byte[]) ois.readObject();
            byte[] deCode = deCode(map, bytes);
            os=new FileOutputStream(destFile);
            os.write(deCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void zipFile(String srcFile, String destFile) {

        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            //从源文件中读出数据
            is = new FileInputStream(srcFile);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //压缩bytes
            byte[] zip = zip(bytes);
            //把压缩后的bytes写入(输出)到目标文件
            //1.创建输出流
            os = new FileOutputStream(destFile);
            //用对象流包裹输出流,即以对象的形式输出到目标文件
            oos = new ObjectOutputStream(os);
            //把byte(字符)与编码(路径)的映射、压缩后的bytes通过oos写入
            oos.writeObject(huffmanCodes);
            oos.writeObject(zip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static byte[] deCode(HashMap<Byte, String> huffmanCodes, byte[] zip) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < zip.length; i++) {
            boolean flag = (i != zip.length - 1);
            String s = byteToBitString(flag, zip[i]);
            stringBuilder.append(s);
        }

        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> byteStringEntry : huffmanCodes.entrySet()) {
            map.put(byteStringEntry.getValue(), byteStringEntry.getKey());
        }
        ArrayList<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte b = null;

            while (true) {
                String substring = stringBuilder.substring(i, i + count);
                b = map.get(substring);
                if (b == null) {
                    count++;
                } else {
                    break;
                }
            }

            bytes.add(b);
            i += count;
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bytes.get(i);
        }
        return result;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        /*
         * 一般情况下都要使s的长度为8，只有碰到最后一个byte使不需要补足8位
         * 但如果最后一个byte是负数时，因为生成的temp有32位，所以让它保留8位(不一定正确)
         * */
        if (flag || temp < 0) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    private static byte[] zip(byte[] bytes) {

        //初始化各个结点
        List<Node> nodes = getNodes(bytes);
        //创建赫夫曼数
        Node huffmanTree = createHuffmanTree(nodes);
        //赫夫曼编码
        huffmanCode(huffmanTree);
        //根据赫夫曼编码压缩bytes后返回
        return zip(bytes, huffmanCodes);
    }

    /*
     *
     * byte(int val) =-88是怎么得到的
     * 正常看来 把字符串"10101000"当成二进制 可以转换成十进制的168，
     * 即十进制的168，转换成二进制为10101000
     * byte(10101000)怎么被计算机输出-88的呢
     * 首先该值(10101000)为byte类型
     * byte可以看成补码,即它的原码为
     * 先得到反码 补码-1 10100111
     * 由反码得到原码 11011000
     * 计算机把最高位1看成符号位，即计算机算出原码代表的二进制数为 -1011000
     * -1011000转化成十进制为-88
     * */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len = (stringBuilder.length() + 7) / 8;
        byte[] zipByte = new byte[len];
        int index = 0;

        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String substring;
            if (i + 8 >= stringBuilder.length()) {
                substring = stringBuilder.substring(i, stringBuilder.length());
            } else {
                substring = stringBuilder.substring(i, i + 8);
            }
            zipByte[index] = (byte) Integer.parseInt(substring, 2);
            index++;
        }
        return zipByte;
    }

    private static void huffmanCode(Node root) {
        if (root == null) {
            return;
        }
        huffmanCode(root.left, "0", stringBuilder);
        huffmanCode(root.right, "1", stringBuilder);
    }

    private static void huffmanCode(Node node, String code, StringBuilder string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.append(code);
        if (node != null) {
            if (node.data == null) {
                huffmanCode(node.left, "0", stringBuilder);
                //出递归时node.data!=null;
                huffmanCode(node.right, "1", stringBuilder);
            } else {//找到了叶子结点
                huffmanCodes.put(node.data, stringBuilder.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static List<Node> getNodes(byte[] bytes) {
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }
}

class Node implements Comparable<Node> {

    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}