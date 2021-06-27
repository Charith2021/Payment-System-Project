package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public static  final List<Student> studentsDB=new ArrayList<>();

    static {
        Student s1 = new Student("963320973v", "Charith Gimhana", "Weligama", LocalDate.of(1996, 11, 27), "0785532732", "charithgimhana96@gmail.com");
        Student s2 = new Student("123456789v", "Lasith Malinga", "Rathgama", LocalDate.of(1986, 05, 14), "077123789456", "Malinga@gmail.com");
        Student s3 = new Student("456123789v", "Kusal Mendis", "Moratuwa", LocalDate.of(1995, 02, 18), "078456789123", "Mendis@gmail.com");
        Student s4 = new Student("256154789v", "Kusal Perera", "Colombo", LocalDate.of(1989, 06, 25), "078451239123", "Perera@gmail.com");
        studentsDB.add(s1);
        studentsDB.add(s2);
        studentsDB.add(s3);
        studentsDB.add(s4);
    }

   private static Student getExistingStudent(String nic) {     //me method eken db eke student wa innawada blnawa(aluthin add karana ekawa kalin add welada balnwa. nic eka primary key wage eka baluwama atha)
       for (Student student : studentsDB) {

           if (student.getNic().equals(nic)) {
                 return student;
           }
       }
       return null;
   }

   public static void saveStudent(Student student){
      Student student1=getExistingStudent(student.getNic());
      if(student1 != null){                //null newei nam already exsists
          new Alert(Alert.AlertType.ERROR,"Student Already Exists", ButtonType.OK);
      }
      studentsDB.add(student);     //exist method eken null na kiyala awoth thama aluth eka save wenne
   }

    public static List<Student> findAllStudents(){
        return studentsDB;
    }    //return krnne db/list eka

    public void updateStudent(Student student){
        Student s = findStudent(student.getNic());    //find student eken not found exception ekak enna puluwan.awoth palleha peli deka wada na.nathnam a deka wada
        int index=studentsDB.indexOf(s);
        studentsDB.set(index,student);
    }

    public static Student findStudent(String nic) {
        Student student = getExistingStudent(nic);       //Student type eke student kiyana variable ekak athulata getStudent() method eka return karana value eka dagena
        if (student != null) {
            return student;
        } else {
            new Alert(Alert.AlertType.ERROR, "Student Not Found", ButtonType.OK).show();
        }
        return null;
    }

    public void deleteStudent(String nic) {
        Student student=findStudent(nic);
        studentsDB.remove(student);
    }

    public static List<Student> findStudents(String query){
        List<Student> result = new ArrayList<>();

        for (Student student : studentsDB) {

            if (student.getNic().contains(query) || student.getFullName().contains(query)
                    || student.getAddress().contains(query) || student.getEmail().contains(query) ||
                    student.getDateOfBirth().toString().contains(query) || student.getContact().contains(query)) {
                result.add(student);
            }
        }
        return result;
    }
}
