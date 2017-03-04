package xiong.com.mvptest.util;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AllAnimationUtil {
	public static TranslateAnimation taLeft, taRight, taTop, taBlow; 
	public static void  InitAnima() {  
		  // TODO Auto-generated method stub  
		  taLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f); 
		 
		  taRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f);  
		  taTop = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 1.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f);  
		  taBlow = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f,  
		    Animation.RELATIVE_TO_PARENT, -1.0f,  
		    Animation.RELATIVE_TO_PARENT, 0.0f);  
		  taLeft.setDuration(500);  
		  taRight.setDuration(500);  
		  taTop.setDuration(500);  
		  taBlow.setDuration(500);  
		 }
}
