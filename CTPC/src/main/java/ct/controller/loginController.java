package ct.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ct.pojo.OpenIdJson;
import ct.pojo.User;
import ct.service.userService;
import ct.util.HttpUtil;
import net.sf.json.JSONObject;

@RestController
public class loginController {
	//以下两项上线时需要修改成具体的ID
	 private String appID = "wx46e42d7faaa6fce1";
	  private String appSecret = "e2b520bef4d1bf317c0078501bfe4acc";
	  OpenIdJson openIdJson =null;//此项拿来装用户的openid，用户一进程序就自动获取
	  
	 @Autowired
	 userService userservice;
	  @PostMapping("/loginGetOpenid")
	  public JSONObject userLogin(@RequestParam("code") String code,HttpServletRequest request) throws IOException {
		  JSONObject userJson = null;
		  User user =null;
		//result是换取的String结果
	    String result = "";
	    try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
	      result = HttpUtil.doGet(
	          "https://api.weixin.qq.com/sns/jscode2session?appid="
	              + this.appID + "&secret="
	              + this.appSecret + "&js_code="
	              + code
	              + "&grant_type=authorization_code", null);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    ObjectMapper mapper = new ObjectMapper();
	    //这里利用OpenIdJson拿到数据，里面有用户Openid
	    openIdJson = mapper.readValue(result,OpenIdJson.class);//这一步用工具类openIdJson拿到了result里面的openid
	    //用userService核对是否存在此用户
	    if(userservice.isUserHasOpenId(openIdJson.getOpenid())) {
	    	user = userservice.selectUserByOpenid(openIdJson.getOpenid());
	    	//弃用session，因为微信小程序request每一次都是新的会话
	    	//request.getSession().setAttribute("currentUser", user.getId());//把工号加入session

	    	
	    	userJson = userservice.userToJson(user);//把用户信息加入userJson以返回
	    }
	    //返回用户的角色
	    return userJson;
	  }
	  
	  @PostMapping("/regist")
	  public JSONObject registController(@RequestParam("idInputValue") String id) {
		  Map<String,String> returnJson = new HashMap<String,String>();
		  if(userservice.updateUserOpenid(openIdJson.getOpenid(), id)) {
			  returnJson.put("isSuccessed", "true");
			  returnJson.put("id",userservice.selectUserByOpenid(openIdJson.getOpenid()).getId());
			  returnJson.put("role",userservice.selectUserByOpenid(openIdJson.getOpenid()).getRole());
			  return JSONObject.fromObject(returnJson);
		  }
		  returnJson.put("isSuccessed", "false");
		  return JSONObject.fromObject(returnJson);
			  
		  
	  }
}
