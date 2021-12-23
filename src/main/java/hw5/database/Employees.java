package hw5.database;

import hw5.model.Employee;
import hw5.model.RequestData;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Employees {

    private final static File dataBase = new File("src/main/resources/database/employees.txt");

    public static void addEmployee(Employee employee) {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(dataBase, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(employee.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Employee getEmployeeInfo(RequestData requestData) {
        String firstName = requestData.getFirstName();
        String lastName = requestData.getLastName();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(dataBase);
            bufferedReader = new BufferedReader(fileReader);
            String str = bufferedReader.readLine();
            while (str != null) {
                String[] emp = str.split("\\$");
                if (emp[0].equals(firstName) && emp[2].equals(lastName)) {
                    return new Employee(emp[0],emp[1],emp[2],Integer.parseInt(emp[3]),Integer.parseInt(emp[4]),emp[5],emp[6]);
                }
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Employee uploadEmployeeFromFile(MultipartFile file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (file.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
            int ch = reader.read();
            while (ch != -1) {
                stringBuilder.append((char) ch);
                ch = reader.read();
            }
        }

        String[] employee = stringBuilder.toString().split("\\$");

        if (employee.length == 7)
            return new Employee(employee[0],employee[1],employee[2],
                    Integer.parseInt(employee[3]),Integer.parseInt(employee[4]),employee[5],employee[6]);

        return null;
    }

}
