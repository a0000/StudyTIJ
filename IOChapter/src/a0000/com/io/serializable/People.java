package a0000.com.io.serializable;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/2/3.
 */
public class People extends ArrayList<Person> {
    public People(File fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for (int i=0; i<elements.size(); i++) {
            add(new Person(elements.get(i)));
        }
    }

    public static void main(String[] args) throws Exception{
        File file = new File("People.xml");
        People p = new People(file);
        System.out.println(p);
    }
}
