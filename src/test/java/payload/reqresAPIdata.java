package payload;

import org.json.simple.JSONObject;
import java.util.Random;

public class reqresAPIdata {
	
	Random rand = new Random();
	
	public JSONObject createUsers(){
        JSONObject data = new JSONObject();
        data.put("name", "User" + rand.nextInt(100));
        data.put("job", "leader");
        return data;
    }
}
