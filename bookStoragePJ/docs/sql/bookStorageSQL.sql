/*테이블 생성*/

/*출판사*/
create table publisher(
	pub_reg_num varchar(12) not null,
	pub_name varchar(50) not null,
	pub_addr varchar(255) not null,
	pub_phone varchar(30) not null,
	bank_code varchar(10) not null,
	acc_num varchar(15) not null
);

alter table publisher
add constraint pub_reg_num_pk primary key(pub_reg_num);

alter table publisher
add constraint acc_num_uu unique(acc_num);

/*계약업체*/
create table contractor(
	cont_num integer not null,
	cont_date date not null,
	cont_stat varchar(5) not null,
	pub_reg_num varchar(12) null
);

alter table contractor
add constraint cont_num_pk primary key(cont_num);

alter table contractor
add constraint contractor_pub_reg_num_fk foreign key(pub_reg_num)
references publisher(pub_reg_num) on delete set null;

/*공급도서*/
create table supply_book(
	cont_num integer not null,
	book_num integer not null,
	book_supply_date date not null
);

alter table supply_book
add constraint cont_num_book_num_pk primary key(cont_num, book_num);

alter table supply_book
add constraint supply_book_cont_num_fk foreign key(cont_num)
references contractor(cont_num) on delete cascade,
add constraint supply_book_book_num_fk foreign key(book_num)
references book(book_num) on delete cascade;

/*도서*/
create table book(
	book_num integer not null,
	category_code varchar(5) null,
	book_title varchar(50) not null,
	rental_amount integer not null,
	book_author varchar(50) not null,
	rental_count integer null,
	book_qty integer not null
);

alter table book
add constraint book_num_pk primary key(book_num);

alter table book
add constraint book_category_code_fk foreign key(category_code)
references book_category(category_code) on delete set null;

/*도서분류*/
create table book_category(
	category_code varchar(5) not null,
	category_name varchar(50) not null
);

alter table book_category
add constraint category_code_pk primary key(category_code);

alter table book_category
add constraint category_name_uu unique(category_name);

/*폐기*/
create table disposal(
	book_num integer not null,
	disposal_date date not null,
	disposal_qty integer not null,
	disposal_reason varchar(300) not null
);

alter table disposal
add constraint disposal_book_num_pk primary key(book_num);

alter table disposal
add constraint disposal_book_num_fk foreign key(book_num)
references inventory(book_num) on delete cascade;

/*재고*/
create table inventory(
	book_num integer not null,
	inven_qty integer not null
);

alter table inventory
add constraint inventory_book_num_pk primary key(book_num);

alter table inventory
add constraint inventory_book_num_fk foreign key(book_num)
references book(book_num) on delete cascade;

/*예약*/
create table reserve(
	mem_num varchar(10) not null,
	book_num integer not null,
	reserved_email varchar(5) null
);

alter table reserve
add constraint mem_num_book_num_pk primary key(mem_num, book_num);

alter table reserve
add constraint reserve_mem_num_fk foreign key(mem_num)
references members(mem_num) on delete cascade,
add constraint reserve_book_num_fk foreign key(book_num)
references inventory(book_num) on delete cascade;

/*회원*/
create table members(
	mem_num varchar(10) not null,
	mem_id varchar(12) not null,
	mem_pw varchar(50) not null,
	mem_birth date not null,
	mem_phone varchar(30) not null,
	mem_email varchar(40) not null,
	mem_email_conf varchar(5) null,
	mem_point integer null,
	mem_regist date not null
);

alter table members
add constraint mem_num_pk primary key(mem_num);

alter table members
add constraint mem_id_uu unique(mem_id),
add constraint mem_phone_uu unique(mem_phone),
add constraint mem_email_uu unique(mem_email);

/*대여*/
create table rental(
	rental_num integer not null,
	book_num integer not null,
	mem_num varchar(10) null,
	rental_date date not null,
	rental_qty integer not null,
	return_date date not null,
	rental_extension varchar(5) null
);

alter table rental
add constraint rental_num_book_num_pk primary key(rental_num, book_num);

alter table rental
add constraint rental_book_num_fk foreign key(book_num)
references inventory(book_num) on delete cascade,
add constraint rental_mem_num_fk foreign key(mem_num)
references members(mem_num) on delete set null;

/*발송*/
create table delivery(
	delivery_num varchar(20) not null,
	rental_num integer null,
	book_num integer null,
	delivery_addr varchar(255) not null,
	delivery_phone varchar(30) not null
);

alter table delivery
add constraint delivery_num_pk primary key(delivery_num);

alter table delivery
add constraint rental_num_book_num_fk foreign key(rental_num, book_num)
references rental(rental_num, book_num) on delete set null;

/*회수*/
create table return(
	delivery_num varchar(20) not null,
	return_addr varchar(255) not null,
	return_phone varchar(30) not null
);

alter table return
add constraint return_delivery_num_pk primary key(delivery_num);

alter table return
add constraint return_delivery_num_fk foreign key(delivery_num)
references delivery(delivery_num) on delete cascade;

/*후기*/
create table review(
	review_num integer not null,
	rental_num integer not null,
	book_num integer not null,
	mem_num varchar(10) null,
	review_date date not null,
	review_content varchar(300) not null,
	review_rating integer not null
);

alter table review
add constraint review_num_rental_num_book_num_pk primary key(review_num, rental_num, book_num);

alter table review
add constraint rental_num_book_num_fk foreign key(rental_num, book_num)
references rental(rental_num, book_num) on delete cascade,
add constraint review_member_num_fk foreign key(mem_num)
references members(mem_num) on delete set null;
