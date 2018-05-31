import com.google.gson.JsonArray;

/**
 * Created by jc on 5/24/18.
 */
public class Main {

    public static void main(String[] args) {
        Client chain = new Client(8888);
        chain.getInfo();

        Client wallet = new Client(9999);
        wallet.getWalletList();

        JsonArray accountAndPassword = new JsonArray();
        accountAndPassword.add("default");
        accountAndPassword.add("PW5KFWYKqvt63d4iNvedfDEPVZL227D3RQ1zpVFzuUwhMAJmRAYyX");

        wallet.unlockWallet(accountAndPassword);


    }

}
