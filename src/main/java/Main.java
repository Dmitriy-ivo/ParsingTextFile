import java.io.*;

public class Main {

    public static String result     = "";
    public static String wordSearch = "NStr(";
    public static String param      = "";
    public static String[] token;
    public static int indexStart    = 0;
    public static int indexEnd      = 0;
    public static int nline         = 0;

    public static void main(String[] args) {

        try {
            File file             = new File("src/main/resources/ObjectModule.bsl");
            FileReader fr         = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            while (line != null) {
                nline = nline + 1;
                while (line.contains(wordSearch)) {

                    indexStart = line.indexOf(wordSearch) + 6;
                    indexEnd   = line.indexOf("\'\"",indexStart);

                    if (indexEnd > 0){
                        line = getParam(line);
                    }else {
                        break;
                    }
                }

                if (indexEnd < 0){
                    line = line + reader.readLine();
                }else {
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter writer = new FileWriter("src/main/resources/result.txt", false))
        {
            writer.write(result);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String getParam(String line){

            param = line.substring(indexStart,indexEnd);
            param = param.replace("\'","");
            param = param.replace("|","");
            param = param.replace("=",":");
            token = param.split(";");

            for (String str : token) {
                System.out.println(nline + " : " + str.trim());
                result = result + nline + " : " + str.trim() + "\n";
            }

        return line.substring(indexEnd);
    }

}
