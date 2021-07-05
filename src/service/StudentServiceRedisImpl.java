package service;

import model.Student;
import redis.clients.jedis.Jedis;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;
import util.JedisClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentServiceRedisImpl {

    private final Jedis client;

    public StudentServiceRedisImpl() {

        client = JedisClient.getInstance().getClient();
    }

    public void saveStudent(Student student) throws DuplicateEntryException {
        Jedis client = new Jedis("localhost", 9090);
        client.auth("123");

        if (client.exists(student.getNic())) {
            throw new DuplicateEntryException();
        }
        client.hset(student.getNic(), student.toMap());
    }


    public void updateStudent(Student student) throws NotFoundException {
        if (!client.exists(student.getNic())) {
            throw new NotFoundException();
        }
        client.hset(student.getNic(), student.toMap());
    }



    public void deleteStudent(String nic) throws NotFoundException {
        if (!client.exists( nic)) {
            throw new NotFoundException();
        }
        client.del(nic);
    }

    private boolean existsStudent(String nic) {
        return client.exists(nic);
    }



  /*  private Student getStudent(String nic){          //we made this private method.than kepayaka use wena nisa method ekakata gattha
       return null;
    }   */         //the exists method had been written instead of this method. only the name is different


    public Student findStudent(String nic) throws NotFoundException {
        if (!client.exists(nic)) {
            throw new NotFoundException();
        }
        return Student.fromMap(nic, client.hgetAll(nic));
    }


    public List<Student> findAllStudents(){
        List<Student> studentList = new ArrayList<>();
        Set<String> nicList = client.keys( "*");

        for (String nic : nicList) {
            studentList.add(Student.fromMap(nic, client.hgetAll(nic)));
        }
        return studentList;
    }

    public List<Student> findStudents(String query){
        List<Student> searchResult = new ArrayList<>();
        Set<String> nicList = client.keys("*");

        for (String nic : nicList) {

            if (nic.contains(query)){
                searchResult.add(Student.fromMap(nic, client.hgetAll(nic)));
            }else{
                List<String> data = client.hvals( nic);

                for (String value : data) {
                    if (value.contains(query)){
                        searchResult.add(Student.fromMap(nic, client.hgetAll(nic)));
                        break;
                    }
                }
            }
        }

        return searchResult;
    }

}
