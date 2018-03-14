SET DATABASE SQL SYNTAX ORA TRUE;

/*==============================================================*/
/* Table: "STUDENT"                                             */
/*==============================================================*/
CREATE TABLE "STUDENT"
(
  "STUDENTID"          INTEGER              NOT NULL,
  "CLASSID"            INTEGER              NOT NULL,
  "STUDENTNAME"        VARCHAR2(50),
  "STUDENTSEX"         VARCHAR2(1),
  CONSTRAINT PK_STUDENT PRIMARY KEY ("STUDENTID")
);
INSERT into STUDENT VALUES (1,1,'libiquan','1');