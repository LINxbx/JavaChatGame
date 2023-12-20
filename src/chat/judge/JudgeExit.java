package chat.judge;

import chat.mysql.AddInformation;
import chat.mysql.Check;
import java.sql.ResultSet;


public class JudgeExit {
    /*
    第一次使用了IO进行文本的存储，后面进行优化后使用MySQL进行数据存储
    保留了第一次IO进行数据存储的代码
     */
    static String filePath = "src/Chat/user.txt";

    //写入账号，密码，用户名
    public static void writeUser(String account, String password, String name) throws Exception {
        int num = 2000 + (int) (Math.random() * 9000);  //生成随机端口
        /*
        //创建文件写入流
        FileWriter fw = new FileWriter(filePath, true);
        //将用户信息写入文件
        fw.write(account + " " + password + " " + name + " " + num + "\n");
        fw.close();//关闭写入流，释放系统资源
         */
        AddInformation.add(account, password, name, num);
    }

    //登录时判断账号密码是否正确
    public static boolean isPassword(String account, String password) throws Exception {
        /*
        //获取 读取保存用户信息的文件路径
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {        //readLine是读取一行数据
            String[] str = line.split(" ");
            if (str[0].equals(account) && str[1].equals(password))
                return true;
        }
        return false;
         */
        try (ResultSet resultSet = Check.MyAccount(account)) {
            if(resultSet.next()) {
                if (password.equals(resultSet.getString("password"))) {
                    return true;
                }
            }
        }
        return false;
    }


    //获取用户的端口号
    public static String getUserNum(String account, String password) throws Exception {
        /*
        //获取 读取保存用户信息的文件路径
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);     //缓冲字符输入流 具有缓冲功能，它可以提供更高效的读取操作。
        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split(" ");
            //判断账号和密码是否正确
            if (str[0].equals(account) && str[1].equals(password)) {
                return str[3];//返回端口号
            }
        }
        br.close();
        fr.close();
        return "端口不存在";
         */
        try (ResultSet resultSet = Check.MyAccount(account)) {
            if(resultSet.next()){
            if (password.equals(resultSet.getString("password"))) {
                return resultSet.getString("port");
            }
            }
        }
        return "端口不存在";
    }

    //注册时判断账号是否存在
    public static boolean isUser(String account) throws Exception {
        /*
        //获取 读取保存用户信息的文件路径
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] str = line.split(" ");
            if (str[0].equals(account))    //若存在返回false
                return false;
        }
        return true;
    }
         */
        try (ResultSet resultSet = Check.MyAccount(account)) {
            if(resultSet.next()) {
                if (resultSet.getString("password") != "") {
                    return false;
                }
            }
        }
        return true;
    }
}
