import java.io.*;
import java.util.Scanner;

public class yq1 {
    public static void main(String args[]) {
        System.out.println("请输入文件名：");
        Scanner input_a = new Scanner(System.in);
        String[] input = input_a.nextLine().split(" ");
        String fileinname = input[0];
        String fileoutname = input[1];
        String ofprovince = null;
        if(input.length > 2){
            ofprovince = input[2];
            //System.out.println(ofprovince);
        }

        try {
            FileInputStream fileinputstream = new FileInputStream(fileinname);
            FileOutputStream fileoutputstream = new FileOutputStream(fileoutname);
            InputStreamReader reader = new InputStreamReader(fileinputstream, "gbk");
            BufferedReader bufferedreader = new BufferedReader(reader);
            OutputStreamWriter writer = new OutputStreamWriter(fileoutputstream, "gbk");
            BufferedWriter bufferedwriter = new BufferedWriter(writer);

            String content = "";
            String province_value = "";
            String str = "待明确地区";
            while((content = bufferedreader.readLine()) != null) {
                String front = content.substring(0,3);
                String remaining = content.substring(4);
                if(content.contains(str)) continue;
                if(ofprovince != null && !ofprovince.equals(front)) continue;
                if (!(province_value.equals(front))) {
                    if (province_value != "") {
                        bufferedwriter.write("\r\n");
                    }
                    bufferedwriter.write(front + "\r\n");
                    province_value = front;
                }
                bufferedwriter.write(remaining + "\r\n");
                bufferedwriter.flush();// 把缓存区内容压入文件
            }
            System.out.println("成功筛选出" + ofprovince + "信息！");

            fileinputstream.close();
            fileoutputstream.close();
            reader.close();
            writer.close();
            bufferedreader.close();
            bufferedwriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

