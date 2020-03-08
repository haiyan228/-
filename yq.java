import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class yq {

	public static void main(String[] args) throws Exception {
		FileReader file = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(file);
		FileOutputStream fos = new FileOutputStream("args[1]");
		
		String line = br.readLine();
		String shen = "";
		int count = 0;
		
		while (line != null){
			String[] word = line.split("\t");
			if(word[0].equals(shen) && Integer.parseInt(word[2]) != 0) {
				String data = word[1] + "\t" + word[2] +"\n";
				fos.write(data.getBytes());
			}else if(!word[0].equals(shen) && Integer.parseInt(word[2]) != 0){
				shen = word[0];
				if(count == 0) {
					String data = shen + "\n" + word[1] + "\t" + word[2] + "\n";
					fos.write(data.getBytes());
				}else {
					String data = "\n" + shen + '\n' + word[1] + "\t" + word[2] + "\n";
					fos.write(data.getBytes());
				}
				count++;
			}
			line = br.readLine();
		}
		br.close();
		file.close();
		fos.close();
	}

}
