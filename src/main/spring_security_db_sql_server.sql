-- Create table
CREATE TABLE APP_USER
(
  USER_ID           BIGINT identity (1,1) NOT NULL,
  USER_NAME         NVARCHAR(36) NOT NULL,
  ENCRYTED_PASSWORD NVARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL DEFAULT '1'
) ;
--  
ALTER TABLE APP_USER
  ADD CONSTRAINT APP_USER_PK PRIMARY KEY (USER_ID);
 
ALTER TABLE APP_USER
  ADD CONSTRAINT APP_USER_UK UNIQUE (USER_NAME);
 
 
-- Create table
CREATE TABLE APP_ROLE
(
  ROLE_ID   BIGINT NOT NULL,
  ROLE_NAME VARCHAR(30) NOT NULL
) ;
--  
ALTER TABLE APP_ROLE
  ADD CONSTRAINT APP_ROLE_PK PRIMARY KEY (ROLE_ID);
 
ALTER TABLE APP_ROLE
  ADD CONSTRAINT APP_ROLE_UK UNIQUE (ROLE_NAME);
 
 
-- Create table
CREATE TABLE USER_ROLE
(
  ID      BIGINT NOT NULL,
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL
);
--  
ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_PK PRIMARY KEY (ID);
 
ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_UK UNIQUE (USER_ID, ROLE_ID);
 
ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_FK1 FOREIGN KEY (USER_ID)
  REFERENCES APP_USER (USER_ID);
 
ALTER TABLE USER_ROLE
  ADD CONSTRAINT USER_ROLE_FK2 FOREIGN KEY (ROLE_ID)
  REFERENCES APP_ROLE (ROLE_ID);
 
 
-- Used by Spring Remember Me API.  
CREATE TABLE Persistent_Logins (
 
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
     
);
 
--------------------------------------
 
INSERT INTO App_User ( USER_NAME, ENCRYTED_PASSWORD, ENABLED)
VALUES ( 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
INSERT INTO App_User (USER_NAME, ENCRYTED_PASSWORD, ENABLED)
VALUES ('dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
---
 
INSERT INTO app_role (ROLE_ID, ROLE_NAME)
VALUES (1, 'ROLE_ADMIN');
 
INSERT INTO app_role (ROLE_ID, ROLE_NAME)
VALUES (2, 'ROLE_USER');
 
---
 
INSERT INTO user_role (ID, USER_ID, ROLE_ID)
VALUES (1, 1, 1);
 
INSERT INTO user_role (ID, USER_ID, ROLE_ID)
VALUES (2, 1, 2);
 
INSERT INTO user_role (ID, USER_ID, ROLE_ID)
VALUES (3, 2, 2);


select * from APP_USER
--- Bang User_CMS ----------------------------------------------------------------------------
                  USE [smsvnet51]
GO

/****** Object:  Table [dbo].[USERS_CMS]    Script Date: 2/17/2023 11:21:28 AM ******/
SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[USERS_CMS](
    [UserID] [int] NOT NULL,
    [Username] [nvarchar](15) NOT NULL,
    [Passwords] [nvarchar](50) NULL,
    [CustomerID] [int] NULL,
    [email] [nvarchar](80) NOT NULL,
    [Create_Time] [datetime] NULL,
    [IP_Address] [nvarchar](60) NULL,
    [Company] [nvarchar](200) NULL,
    [Fullname] [nvarchar](80) NULL,
    [Address] [nvarchar](200) NULL,
    [Question] [nvarchar](80) NULL,
    [answer] [nvarchar](80) NULL,
    [Levels] [int] NULL,
    [Sharekey] [nvarchar](50) NULL,
    [Enable] [bit] NULL,
    [Prepaid] [bit] NULL,
    [Priority] [tinyint] NULL,
    [BrandnameNoCheck] [bit] NULL,
    [Last_Login] [datetime] NULL,
    [Last_Modify] [datetime] NULL,
    [ModifyID] [int] NULL,
    [CreateID] [int] NULL,
    [SMPP_Enable] [bit] NULL,
    [HTTP_Enable] [bit] NULL,
    [WebSerice_Enable] [bit] NULL,
    [Timeout] [tinyint] NULL,
    [SessionId] [varchar](32) NULL,
    [Password] [nvarchar](50) NULL,
    [Message_Double] [bit] NULL,
    [email_statistics] [bit] NULL,
    [Allow_Type_Msg] [int] NULL,
    [phone] [varchar](16) NULL,
    [email_alert] [varchar](50) NULL,
    [rowguid] [uniqueidentifier] NOT NULL,
    [ID] [bigint] IDENTITY(1,1) NOT NULL,
    [AllowSocialMsg] [bit] NULL,
    [CashBalance] [bigint] NULL,
    [CashLow] [int] NULL,
    [Created_By] [bigint] NULL,
    [Currency] [nvarchar](30) NULL,
    [Notification] [int] NULL,
    [updated_By] [bigint] NULL,
    [updated_Time] [datetime] NULL
    ) ON [PRIMARY]
    GO

ALTER TABLE [dbo].[USERS_CMS] ADD  DEFAULT ((1)) FOR [AllowSocialMsg]
    GO
----------------------------------------------------------------------------
--- Table LOG_ACTION -------------------------------------------------
CREATE TABLE [dbo].[log_action](
    [id] [bigint] IDENTITY(1,1) NOT NULL,
    [action] [nvarchar](100) NULL,
    [description] [nvarchar](1000) NULL,
    [created_time] [datetime] NULL,
    [updated_by] [bigint] NULL
    ) ON [PRIMARY]
    GO

ALTER TABLE [dbo].[log_action] ADD  CONSTRAINT [DF_log_action_created_time]  DEFAULT (getdate()) FOR [created_time]
    GO
-----------------------------------------------------------------------------------------------
