import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Arrays;

public class yq_4 {

    public static void main(String[] args) throws Exception {
        // 定义输入数据的路径
        FileReader file = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(file);
        // 定义输出数据的路径
        FileOutputStream fos = new FileOutputStream(args[1]);
        // 一次读取一行
        String line = br.readLine();
        // 初始化省份为空
        String shen = "";
        // 用于计数来输出文件中第一个省份
        int count = 0;
        int sum = 0;

        // 如果输入的数据只有输入路径和输出路径两个则进行输出所有省的信息
        if (args.length == 2) {
            String[] shenfen = new String[3000];
            // 循环读取一行
            while (line != null) {
                // 对读取的行进行分割
                String[] word = line.split("\t");
                // 如果读取的一行和定义的省份一样和人数不为0的时候进行输出data
                if (word[0].equals(shen) && Integer.parseInt(word[2]) != 0) {
                    sum += Integer.parseInt(word[2]);
                } else if (!word[0].equals(shen) && Integer.parseInt(word[2]) != 0) {
                    // 将省份定义为读取到的省份
                    shenfen[sum] = shen;
                    sum = 0;
                    shen = word[0];
                    // 如果读取的是第一行
                    if (count == 0) {
                        sum += Integer.parseInt(word[2]);
                        // 如果读取的不是第一行
                    } else {
                        sum += Integer.parseInt(word[2]);
                    }
                    count++;
                }
                // 继续读取一行
                line = br.readLine();
            }
            shenfen[sum] = shen;
            for(int i = 2999; i >= 0; i--) {
                if(shenfen[i] != null) {
                    write_for_count(shenfen[i],args[0],args[1],fos);
                }
            }
            // 如果输入的数据有输入路径和输出路径和省份
        } else {

            String data = args[2] + "\n";
            // 写出data
            fos.write(data.getBytes());
            // 如果能读出数据循环
            String[] city = new String[1000];
            while (line != null) {
                // 对读取的数据分割
                String[] word = line.split("\t");
                // 如果省份为定义的省份，并且人数不为0
                if (word[0].equals(args[2]) && !word[2].equals("0")) {
                    int con = Integer.parseInt(word[2]);
                    if (city[con] == null)
                        city[con] = word[1];
                    else
                        city[con] = city[con] + "," + word[1];
                }
                // 读取一行
                line = br.readLine();
            }
            for (int i = 999; i >= 0; i--) {
                if (city[i] != null) {
                    String[] split = city[i].split(",");
                    if (split.length == 1) {
                        data = city[i] + "\t" + i + "\n";
                        fos.write(data.getBytes());
                    } else {
                        Arrays.sort(split);
                        for (String string : split) {
                            data = string + "\t" + i + "\n";
                            fos.write(data.getBytes());
                        }
                    }
                }
            }
        }
        // 关闭流
        br.close();
        file.close();
        fos.close();
    }

    private static void write_for_count(String shen, String inpath, String outpath, FileOutputStream fos) throws Exception {
        FileReader file = new FileReader(inpath);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        while(line != null) {
            String data = "\n"+shen + "\n";
            // 写出data
            fos.write(data.getBytes());
            // 如果能读出数据循环
            String[] city = new String[1000];
            while (line != null) {
                // 对读取的数据分割
                String[] word = line.split("\t");
                // 如果省份为定义的省份，并且人数不为0
                if (word[0].equals(shen) && !word[2].equals("0")) {
                    int con = Integer.parseInt(word[2]);
                    if (city[con] == null)
                        city[con] = word[1];
                    else
                        city[con] = city[con] + "," + word[1];
                }
                // 读取一行
                line = br.readLine();
            }
            for (int i = 999; i >= 0; i--) {
                if (city[i] != null) {
                    String[] split = city[i].split(",");
                    if (split.length == 1) {
                        data = city[i] + "\t" + i + "\n";
                        fos.write(data.getBytes());
                    } else {
                        Arrays.sort(split);
                        for (String string : split) {
                            data = string + "\t" + i + "\n";
                            fos.write(data.getBytes());
                        }
                    }
                }
            }
        }
        br.close();
        file.close();
    }
}

