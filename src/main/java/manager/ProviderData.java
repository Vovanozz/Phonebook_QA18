package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProviderData {

    @DataProvider
    public Iterator<Object[]> loginModelDto(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{User.builder().email("v762900819@gmail.com").password("Vova1234$").build()});
        list.add(new Object[]{User.builder().email("vladimirozz1@gmail.com").password("Vova1234$").build()});
        list.add(new Object[]{User.builder().email("vladimirozz2@gmail.com").password("Vova1234$").build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginRegistrationUserFromFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader=new BufferedReader(new FileReader("src/test/resources/data.csv"));
        String line = reader.readLine();

        while (line !=null) {
            String[] split = line.split(",");
            list.add(new Object[]{ User.builder().email(split[0]).password(split[1]).build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
