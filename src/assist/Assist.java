package assist;

import java.io.File;

public class Assist {
	public void WorkDirectory(File file){
		if(file.isDirectory()){
			for(File f : file.listFiles()){
				WorkDirectory(f);
			}
		}
		else{
			System.out.println(file.getName());
		}
	}
}
