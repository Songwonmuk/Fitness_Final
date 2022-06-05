DROP SEQUENCE MEMBER_MEMBERID_SEQ;
DROP TABLE Member;
DROP SEQUENCE BodyProfile_ProfileNum_SEQ;
DROP TABLE BodyProfile;
DROP SEQUENCE Manager_ManagerID_SEQ;
DROP TABLE Manager;
DROP SEQUENCE Exercise_ExerNum_SEQ;
DROP TABLE Exercise;
DROP SEQUENCE Program_ProgramID_SEQ;
DROP TABLE Program;


CREATE TABLE Member
(
	memberID NUMBER PRIMARY KEY,  --회원 아이디
	memberPW VARCHAR2(20) NOT NULL,      --비밀번호
	memberName VARCHAR2(30) NOT NULL,  -- 이름
	phone VARCHAR2(20) NOT NULL,               -- 연락처
	gender VARCHAR2(2) CHECK (GENDER IN('남', '여')),  
	birth date NOT NULL,
    joindate date default sysdate  
);
CREATE SEQUENCE MEMBER_MEMBERID_SEQ;

--운동 관리자 테이블
CREATE TABLE BodyProfile
(
    profileNum number PRIMARY KEY,
    memberID number,
    weight number(6,2) NOT NULL,
    height number(6,2) NOT NULL,
    smm number(6,2),--Skeletall Muscle Mass
    bmi number(6,2),
    balance number(6,2),
    stress number(6,2),
    programID number,
    opinion varchar2(300),
    CONSTRAINT FK_memberID foreign KEY(memberID) references Member (memberID),
    CONSTRAINT FK_BodyProfile_programID foreign KEY(programID) references Program (programID)
);
CREATE SEQUENCE BodyProfile_ProfileNum_SEQ;

--전체 운동 목록 테이블
CREATE TABLE Manager
(
    managerID number PRIMARY KEY,
    managerName varchar2(30),
    jobPeriod number(10),
    influenceLevel varchar(4),
    comments varchar2(300)
);
CREATE SEQUENCE Manager_ManagerID_SEQ;
-- 운동 프로그램 테이블
 
CREATE TABLE Exercise
(
    exerNum number PRIMARY KEY,
    programID number,
    exerName varchar2(100),
    exerType varchar2(20) check (ExerType in('근력','다이어트','밸런스','마음안정')),
    exerTime number(4),
    consumedCalory number(5,2),
    gainedMuscle number(5,2),
    getBalance number(5,2),
    lowerStress number(5,2),
    CONSTRAINT FK_ProgramID foreign KEY(programID) references Program (programID)
);
CREATE SEQUENCE Exercise_ExerNum_SEQ;

CREATE TABLE Program
(
    programID number PRIMARY KEY,
    exerList list,   
    managerID number,
    programName varchar2(100) NOT NULL,
    programInfo varchar2(300) NOT NULL,
    totalCalory number,
    totalMuscle number,
    totalBalance number,
    totalStress number,
    CONSTRAINT FK_ManagerID foreign KEY(managerID) references Manager (managerID)
);
CREATE SEQUENCE Program_ProgramID_SEQ;

