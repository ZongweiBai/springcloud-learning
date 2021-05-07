CREATE TABLE oauth_client_details (
  client_id   varchar2(48) NOT NULL ,
  resource_ids varchar2(256) DEFAULT NULL,
  client_secret varchar2(256) DEFAULT NULL,
  scope clob DEFAULT NULL,
  authorized_grant_types varchar2(256) DEFAULT NULL,
  web_server_redirect_uri varchar2(256) DEFAULT NULL,
  authorities varchar2(256) DEFAULT NULL,
  access_token_validity number(11) DEFAULT NULL,
  refresh_token_validity number(11) DEFAULT NULL,
  additional_information clob DEFAULT NULL,
  autoapprove varchar2(256) DEFAULT NULL,
  PRIMARY KEY (client_id)
);

-- 初始化client，clientId:oauth_client, clientSecret:oauth_client_9527
Insert into OAUTH_CLIENT_DETAILS (CLIENT_ID,RESOURCE_IDS,CLIENT_SECRET,SCOPE,AUTHORIZED_GRANT_TYPES,WEB_SERVER_REDIRECT_URI,AUTHORITIES,ACCESS_TOKEN_VALIDITY,REFRESH_TOKEN_VALIDITY,ADDITIONAL_INFORMATION,AUTOAPPROVE) values ('oauth_client',null,'$2a$10$BzHyuqFxz3NECG3FAcgPKO2ZLpA/E6XiM9pfMnZ1hHVeipl9N1NTu','all,read,write','refresh_token,password,authorization_code','http://www.baidu.com',null,3600,-1,'{"location":"ALL"}',null);
commit;

-- 自定义oauth2的权限表
CREATE TABLE oauth_user_authority (
  authority_id NUMBER(20, 0) NOT NULL,
  user_id   varchar2(32) NOT NULL ,
  authority_type varchar2(32) NOT NULL,
  authority clob NOT NULL,
  PRIMARY KEY (authority_id)
);

-- oauth_client_details的扩展，定义信任IP、访问频率等
CREATE TABLE oauth_client_acl (
  client_id   varchar2(48) NOT NULL ,
  allow_list  varchar2(1024),
  block_list  varchar2(1024),
  daily_max_access NUMBER (10, 0),
  PRIMARY KEY (client_id)
)