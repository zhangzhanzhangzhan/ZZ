package com.neo.mapper;


import com.neo.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

	@Select("SELECT * FROM user WHERE userName = #{userName} and userPassword=#{userPassword}")
	User getOneByNameAndPass(User user);

	@Select("SELECT * FROM manager WHERE managerName = #{managerName} and managerPassword=#{managerPassword}")
	Manager getManager(Manager manager);

	@Select("<script> SELECT * FROM book where 1=1 <if test='bookName!=null'> and bookName like \"%\"#{bookName}\"%\" </if> </script>")
	List<Book> showBookList(Book book);

	@Select("SELECT * FROM collect WHERE userID = #{userID} and bookID=#{bookID}")
	List<Collect> getCollect(Collect collect);

	@Insert("insert into collect(bookID,userID) value(#{bookID},#{userID})")
	int collect(Collect collect);

	@Select("SELECT * FROM collect c left join book b on c.bookID=b.bookID where c.userID=#{userID}")
	List<Collect> showCollectList(Collect collect);

	@Delete("delete FROM collect where collectID=#{collectID} ")
	void deleteCollect(Collect collect);

	@Select("SELECT * FROM borrow WHERE userID = #{userID} and bookID=#{bookID}")
	List<Borrow> getBorrow(Borrow borrow);

	@Insert("insert into borrow(bookID,userID,startTime,endTime) value(#{bookID},#{userID},NOW(),date_add(NOW(), interval 1 MONTH))")
	int borrow(Borrow borrow);

	@Select("SELECT * FROM borrow c left join book b on c.bookID=b.bookID where c.userID=#{userID}")
	List<Borrow> showBorrowList(Borrow borrow);

	@Select("SELECT * FROM user ")
	List<User> showUserList(User user);

	@Delete("delete FROM user where userID=#{userID} ")
	void deleteUser(User user);

	@Select("SELECT * FROM user WHERE userName= #{userName}")
	List<User> getRegister(User user);

	@Insert("insert into user (userName,userPassword) value(#{userName},#{userPassword})")
	void insert(User user);

	@Update("update user set userPassword=#{userPassword} where userID=#{userID}")
	void editUser(User user);

	@Update("update book set amount=amount+#{amount} where bookID=#{bookID}")
	void editBookAmount(Book book);

	@Delete("delete FROM book where bookID=#{bookID} ")
	void deleteBook(Book book);

	@Insert("insert into book (bookName,author,time,bookIntroduce,authorIntroduce,amount) value(#{bookName},#{author},#{time},#{bookIntroduce},#{authorIntroduce},#{amount})")
	void addBook(Book book);

	@Select("SELECT * FROM manager ")
	List<Manager> showManagerList(Manager manager);

	@Delete("delete FROM manager where managerID=#{managerID} ")
	void deleteManager(Manager manager);

	@Select("SELECT * FROM manager WHERE managerName= #{managerName}")
	List<Manager> getRegisterMa(Manager manager);

	@Insert("insert into manager (managerName,managerPassword) value(#{managerName},#{managerPassword})")
	void insertManager(Manager manager);

	@Insert("insert into booking (bookID,userID,bookingTime) value(#{bookID},#{userID},#{bookingTime})")
	int addBooking(Booking booking);

	@Select("SELECT * FROM booking WHERE userID = #{userID} and bookID=#{bookID}")
	List<Booking> getBooking(Booking booking);

	@Select("SELECT * FROM booking c left join book b on c.bookID=b.bookID where c.userID=#{userID}")
	List<Booking> showBookingList(Booking booking);
}