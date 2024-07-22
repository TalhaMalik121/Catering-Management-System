import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {

        FileInputStream signUpFileIStream = null;
        ObjectInputStream signUpObjectIStream = null;

        FileInputStream menuFileIStream = null;
        ObjectInputStream menuObjectIStream = null;

        FileInputStream orderFileIStream = null;
        ObjectInputStream orderObjectIStream = null;
        try{
            signUpFileIStream = new FileInputStream("SignUpObj.txt");
            signUpObjectIStream = new ObjectInputStream(signUpFileIStream);
            menuFileIStream = new FileInputStream("MenuObj.txt");
            menuObjectIStream = new ObjectInputStream(menuFileIStream);
            orderFileIStream = new FileInputStream("OrderObj.txt");
            orderObjectIStream = new ObjectInputStream(orderFileIStream);

            while(true){
                try {
                    SignupBackend data = (SignupBackend) signUpObjectIStream.readObject();
                    SignupGui.SignupArraylist.add(data);
                }
                catch (EOFException ee){
                    break;
                }
            }
            while(true){
                try {
                    MenuBackend data = (MenuBackend) menuObjectIStream.readObject();
                    MenuGUI.menuArrayList.add(data);
                }
                catch (EOFException ee){
                    break;
                }
            }

            while(true){
                try {
                    OrderBackend data = (OrderBackend) orderObjectIStream.readObject();
                    OrderGUI.OrdersList.add(data);

                }
                catch (EOFException ee){
                    break;
                }

            }
        }
        catch(Exception e){
        }
        finally {
            try {
                signUpObjectIStream.close();
                signUpFileIStream.close();
                menuObjectIStream.close();
                menuFileIStream.close();
                orderObjectIStream.close();
                orderFileIStream.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        SignupGui signupGui = new SignupGui();
        LoginGui loginObj=new LoginGui(signupGui);

    }
}