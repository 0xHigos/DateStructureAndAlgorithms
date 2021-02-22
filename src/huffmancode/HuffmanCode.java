package huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        byte[] zip = huffmanZip(bytes);
        System.out.println(Arrays.toString(zip));
        byte[] decode = decode(zip);
        System.out.println(new String(decode));*/

        String srcFile = "D://test.zip";
        String dstFile = "D://test2.bmp";
        unZipFile(srcFile, dstFile);
        System.out.println("解压缩成功");

    }

    private static void zipFile(String srcFile, String dstFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(dstFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            byte[] huffmanZip = huffmanZip(b);
            objectOutputStream.writeObject(huffmanCodes);
            objectOutputStream.writeObject(huffmanZip);
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                objectOutputStream.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void unZipFile(String srcFile, String dstFile) {
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        OutputStream  outputStream =null;

        try {
            inputStream = new FileInputStream(srcFile);
            objectInputStream = new ObjectInputStream(inputStream);
            outputStream = new FileOutputStream(dstFile);
            huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            byte[] b = (byte[]) objectInputStream.readObject();
            byte[] decode = decode(b);
            outputStream.write(decode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static byte[] decode(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean flag = (i == bytes.length-1);
            stringBuilder.append(byte2BitString(!flag, b));
        }
        Map<String,Byte> byte2String = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            byte2String.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            Byte b = null;
            boolean flag = true;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                if(byte2String.containsKey(key)){
                    b = byte2String.get(key);
                    flag = false;
                }else{
                    count ++;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    private static String byte2BitString(boolean flag, byte b) {
        int temp =b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTree = createHuffmanTree(nodes);
        getCodes(huffmanTree);
        byte[] zipByte = zip(bytes, huffmanCodes);
        return zipByte;
    }

    private static void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空，无法遍历");
        } else {
            root.preOrder();
        }
    }

    private static void getCodes(Node root) {
        if (root == null) {
            System.out.println("树为空，无法进行编码操作");
        }
        getCodes(root.getLeft(), "0", stringBuilder);
        getCodes(root.getRight(), "1", stringBuilder);
    }

    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }

    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilderTemp = new StringBuilder(stringBuilder);
        stringBuilderTemp.append(code);
        if (node != null) {
            if (node.getData() == null) {
                getCodes(node.getLeft(), "0", stringBuilderTemp);
                getCodes(node.getRight(), "1", stringBuilderTemp);
            }else{
                huffmanCodes.put(node.getData(), stringBuilderTemp.toString());
            }
        }
    }

    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            nodes.add(parent);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }
        return nodes.get(0);
    }

    /*
    * @param:
    *   bytes :原始的字节码
    *   huffmanCodes : 对应的字节编码
    *
    * @return:返回一个编码好的byte[]
    * */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int length = (stringBuilder.length() + 7) / 8;
        //创建压缩后的byte[]数组
        byte[] huffmanCodesBytes = new byte[length];
        int index =0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String str;
            if (i + 8 > stringBuilder.length()) {
                str = stringBuilder.substring(i);
            }else{
                str = stringBuilder.substring(i, i + 8);
            }
            huffmanCodesBytes[index++] = (byte) Integer.parseInt(str, 2);
        }
        return huffmanCodesBytes;
    }

}

class Node implements Comparable<Node> {
    private Byte data;
    private int weight;
    private Node left;
    private Node right;

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node[" +
                "data=" + data +
                ", weight=" + weight +
                ']';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
