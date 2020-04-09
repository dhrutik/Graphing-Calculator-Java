package graph;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
public class MathJS {
	protected static String MATHJS_URL = 
            "http://cdnjs.cloudflare.com/ajax/libs/mathjs/1.0.1/math.js";

    protected ScriptEngine engine;
    private int val = -1;

	  public void setValue(int x) {
	    val = x;
	  }

	  public int getValue() {
	    return val;
	  }
    public MathJS() throws MalformedURLException, ScriptException, IOException {
        ScriptEngineManager manager = new ScriptEngineManager ();
        engine = manager.getEngineByName ("js");

        engine.eval(new InputStreamReader(new URL(MATHJS_URL).openStream()));
        engine.eval("var parser = math.parser();");
        engine.eval("var precision = 14;");
    }

    public String eval (double a,String expr)  {
        //String script = "math.format(parser.eval('" + expr + "'), precision);";
        String script = "parser.set('x',"+a+");math.format(parser.eval('" + expr + "'));";
        //String script = "math.evaluate('2^2');";
        String s="";
     try {
    	 s = (String)engine.eval(script);
     }catch(Exception e) {
    	 System.out.println(e.toString());
     }
        return s;
    }
    public static void main(String[] args) throws ScriptException, MalformedURLException, IOException {
        MathJS math = new MathJS();
       // math.eval("a = -10");
        double x =Double.parseDouble(math.eval(-2,"1/(x^2-4)"));
        //if (x==Double.POSITIVE_INFINITY || x==Double.NEGATIVE_INFINITY || x==Double.NaN)
        System.out.println("x="+x);
        /*System.out.println(math.eval("a = 4"));
        System.out.println(math.eval("1.2 * (2 + a)"));
        System.out.println(math.eval("5.08 cm in inch"));
        System.out.println(math.eval("sin(45 deg) ^ 2"));   
        System.out.println(math.eval("9 / 3 + 2i"));    
        System.out.println(math.eval("det([-1, 2; 3, 1])"));
        double x =Double.parseDouble(math.eval("2a^2"));
        System.out.println(x);*/
        
    }
}
