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
	memberID NUMBER PRIMARY KEY,  --ȸ�� ���̵�
	memberPW VARCHAR2(20) NOT NULL,      --��й�ȣ
	memberName VARCHAR2(30) NOT NULL,  -- �̸�
	phone VARCHAR2(20) NOT NULL,               -- ����ó
	gender VARCHAR2(2) CHECK (GENDER IN('��', '��')),  
	birth date NOT NULL,
    joindate date default sysdate  
);
CREATE SEQUENCE MEMBER_MEMBERID_SEQ;

--� ������ ���̺�
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

--��ü � ��� ���̺�
CREATE TABLE Manager
(
    managerID number PRIMARY KEY,
    managerName varchar2(30),
    jobPeriod number(10),
    influenceLevel varchar(4),
    comments varchar2(300)
);
CREATE SEQUENCE Manager_ManagerID_SEQ;
-- � ���α׷� ���̺�
 
CREATE TABLE Exercise
(
    exerNum number PRIMARY KEY,
    programID number,
    exerName varchar2(100),
    exerType varchar2(20) check (ExerType in('�ٷ�','���̾�Ʈ','�뷱��','��������')),
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

