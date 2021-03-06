/** 用户基本信息表 **/
DROP TABLE T_USER;
CREATE TABLE T_USER
(
-- 用户ID唯一
USER_ID INT NOT NULL,
/** 用户昵称 **/
USER_CDE VARCHAR(40),
/** 用户状态  A 有效 N无效 C冻结 **/
USER_STATUS VARCHAR(2),
/** 用户类型 A 管理员 G普通用户**/
USER_TYPE VARCHAR(2),
/** 用户姓名 **/
USER_NAME VARCHAR(40),
/** 用户昵称 **/
USER_NICKNAME VARCHAR(50),
/** 用户手机号 **/
PHONE VARCHAR(40),
/** 用户邮箱 **/
MAIL VARCHAR(40),
/** 证件类型 **/
CERTIF_TYPE VARCHAR(40),
/** 证件号码 **/
CERTIF_NO VARCHAR(40),
/** 用户信用等级 **/
USER_CREDIT VARCHAR(2),
/** 推荐人手机 **/
RECOD_PHONE VARCHAR(40),
/** 创建时间 **/
CREATE_DATE VARCHAR(40),
/** 更新时间 **/
UPDATE_DATE VARCHAR(40),
PRIMARY KEY(USER_ID)
);
/** 用户操作日志表 **/
DROP TABLE T_OPT_LOG;
CREATE TABLE T_OPT_LOG
(
-- ID唯一
CITY_ID INT NOT NULL AUTO_INCREMENT,
/** 用户ID **/
USER_ID INT,
/** 操作状态 0成功**/
OPT_STATUS VARCHAR(20),
/** 操作时间 **/
OPT_DATE VARCHAR(20),
/** 操作类型 0 登录 **/
OPT_TYPE VARCHAR(10),
/** 操作IP **/
OPT_IP VARCHAR(30),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(CITY_ID)
);

/** 用户安全控制表 **/
DROP TABLE T_SECTY_CTL;
CREATE TABLE T_SECTY_CTL
(
-- ID唯一
CITY_ID INT NOT NULL AUTO_INCREMENT,
/** 用户ID **/
USER_ID INT,
/** 登录密码 **/
LOGIN_PWD VARCHAR(50),
/** 支付密码 **/
TRAN_PWD VARCHAR(50),
/** 用户状态 **/
USER_STATUS VARCHAR(2),
/** 登录失败次数 **/
ERR_COUNT INT,
/** 安全级别 **/
SECTY_LEVEL INT,
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 最后更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(CITY_ID)
);
/** 个人账户总表 **/
DROP TABLE T_ACCOUNT;
CREATE TABLE T_ACCOUNT
(
-- 账户 一
ACCOUT_NO INT NOT NULL AUTO_INCREMENT,
/** 用户ID **/
USER_ID INT,
/** 账户类型 **/
ACC_TYPE VARCHAR(50),
/** 账户状态 **/
ACC_STATUS VARCHAR(50),
/** 账户余额 **/
ACC_BALANCE DECIMAL(20,4),
/** 账户冻结金额 **/
ACC_FREEZE DECIMAL(20,4),
/** 账户可用金额 **/
ACC_ENCHASH DECIMAL(20,4),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 最后更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(ACCOUT_NO)
);

/** 个人账户明细表 **/
DROP TABLE T_ACCOUNT_DETAIL;
CREATE TABLE T_ACCOUNT_DETAIL
(
-- ID --
ACC_DEAIL_ID INT NOT NULL AUTO_INCREMENT,
-- 账户 一
ACCOUT_NO INT NOT NULL,
-- 交易流水号
TRANSION_SEQ VARCHAR(50),
/** 收付方式 **/
PAYMENT_TYPE VARCHAR(2),
/** 金额 **/
AMOUNT DECIMAL(20,4),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 最后更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(ACC_DEAIL_ID)
);

/** 系统参数表 **/
DROP TABLE T_SYSTEM_PARA;
CREATE TABLE T_SYSTEM_PARA
(
-- ID --
PARA_ID INT NOT NULL AUTO_INCREMENT,
-- 参数代码 一
PARA_CODE VARCHAR(20) NOT NULL,
/** 参数名称**/
PARA_NAME VARCHAR(50),
/** 参数值 **/
PARA_VALUES VARCHAR(20),
/** 参数类型 **/
PARA_TYPE VARCHAR(20),
/** 参数长度 **/
PARA_LENGTH INT,
/** 说明 **/
REMARK VARCHAR(128),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 最后更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(PARA_ID)
);
/** 用户短信验证码表 **/
DROP TABLE T_CPT_MSG;
CREATE TABLE T_CPT_MSG
(
-- ID --
CPT_ID INT NOT NULL AUTO_INCREMENT,
-- ID --
MSG_ID INT NOT NULL,
-- 手机 一
PHONE_NO VARCHAR(20) NOT NULL,
/** 验证码**/
CPT_NO VARCHAR(20),
/** 发送时间 **/
SEND_TIME VARCHAR(20),
/** 有效时间 **/
ACTIVE_TIME VARCHAR(20),
PRIMARY KEY(CPT_ID)
);
/** 短信日志表 **/
DROP TABLE T_MSG_LOG;
CREATE TABLE T_MSG_LOG
(
-- ID --
MSG_ID INT NOT NULL,
-- 短信业务类型 一
MSG_BIZTYP VARCHAR(20) NOT NULL,
/** 短信发送方式 0 上行 1下行**/
MSG_TYP VARCHAR(20),
/** 发送手机号码 **/
MSG_PHONENO VARCHAR(30),
/** 短信内容 **/
MSG_VALUE VARCHAR(500),
/** 发送时间 **/
SEND_TIME VARCHAR(20),
PRIMARY KEY(MSG_ID)
);
/** 用户银行账户表 **/
DROP TABLE T_BANK_ACC;
CREATE TABLE T_BANK_ACC
(
-- 用户银行ID --
USER_BANK_ACC_ID INT NOT NULL AUTO_INCREMENT,
-- 用户ID 一
USER_ID VARCHAR(20) NOT NULL,
/** 银行代码 **/
BANK_CODE VARCHAR(20),
/** 银行名称 **/
BANK_NAME VARCHAR(30),
/** 开户行名称 **/
BANK_BRANCH_NAME VARCHAR(100),
/** 账号 **/
BANK_ACC_CODE VARCHAR(20),
/** 是否是首选账号 **/
DEFAULT_FLAG VARCHAR(1),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(USER_BANK_ACC_ID)
);

/** 彩票基础投资产品表 **/
DROP TABLE T_BASIC_PRODUCT_STOCK;
CREATE TABLE T_BASIC_PRODUCT_STOCK
(
-- 彩票产品ID --
BASIC_PRODUCT_STOCK_ID INT NOT NULL AUTO_INCREMENT,
-- 基础产品ID 一
BASIC_PRODUCT_ID VARCHAR(20) NOT NULL,
/** 股票代码 **/
INVEST_PRODUCT_ID VARCHAR(20),
/** 申购代码 **/
APPLY_CODE VARCHAR(20),
/** 销售城市 **/
SALES_CITY VARCHAR(30),
/** 单价 **/
PRICE DECIMAL(20,4),
/** 开始时间 **/
START_DATE VARCHAR(20),
/** 结束时间 **/
END_DATE VARCHAR(20),
/** 申购上限 **/
UP_LIMIT  INT,
/** 申购下限 **/
LOW_LIMIT  INT,
-- 资金解冻时间
FUNDFREE_DATE VARCHAR(20),
-- 中签率
LOT_RATE DECIMAL(20,4),
/** 开奖日期 **/
LOT_DATE VARCHAR(20),
/** 中奖尾号 **/
RESULT_NO VARCHAR(1000),
/** 是否公布中奖结果 **/
RESULT_FLAG VARCHAR(1),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(BASIC_PRODUCT_STOCK_ID)
);
/** 基础投资产品表 **/
DROP TABLE T_BASIC_PRODUCT;
CREATE TABLE T_BASIC_PRODUCT
(
-- 基础投资产品ID --
BASIC_PRODUCT_ID INT NOT NULL AUTO_INCREMENT,
-- 基础产品名称 一
BASIC_PRODUCT_NAME VARCHAR(40) NOT NULL,
/** 基础产品类型 **/
BASIC_PRODUCT_TYPE VARCHAR(4),
/** 投资类型 **/
BASIC_INVEST_TYPE VARCHAR(1),
/** 发起人保证金比例 **/
STARTER_BAIL DECIMAL(20,4),
/** 投资人保证金比例 **/
INVESTER_BAIL DECIMAL(20,4),
/** 发起人管理费计提方式 **/
LAUNCHER_ACCRUEDCHARGES_TYPE VARCHAR(1),
/** 发起人管理费计提金额 **/
LAUNCHER_ACCRUEDCHARGES_AMOUNT  DECIMAL(20,4),
/** 投资人管理费计提方式 **/
USER_ACCRUEDCHARGES_TYPE  VARCHAR(1),
/** 投资人管理费计提金额 **/
USER_ACCRUEDCHARGES_AMOUNT DECIMAL(20,4),
/** 基础投资录入页面 **/
INPUT_WEB VARCHAR(50),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 更新时间 **/
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(BASIC_PRODUCT_ID)
);
/** 投资产品基本信息表 **/
DROP TABLE T_INVEST_PRDOUCT;
CREATE TABLE T_INVEST_PRDOUCT
(
-- 投资产品ID --
INVT_PRODUCT_ID INT NOT NULL AUTO_INCREMENT,
-- 发起人ID 一
USER_ID INT NOT NULL,
-- 产品名称 一
INVT_PRODUCT_NAME VARCHAR(40) NOT NULL,
-- 风险保证级别 一
RISK_LVL VARCHAR(1),
-- 产品状态 一
INVT_PRODUCT_STATUS VARCHAR(2),
-- 发起时间 一
START_DATE VARCHAR(20),
-- 终止时间 一
END_DATE VARCHAR(20),
-- 总金额 --
AMOUNT VARCHAR(20),
/** 产品协议 **/
PROTOL_ID VARCHAR(50),
/** 产品发起管理费  **/
ISSUE_FEE DECIMAL(20,4),
/** 投资类型 **/
BASIC_INVEST_TYPE VARCHAR(1),
/** 发起人保证金比例 **/
STARTER_BAIL DECIMAL(20,4),
/** 投资人保证金比例 **/
INVESTER_BAIL DECIMAL(20,4),
/** 发起人管理费计提方式 **/
LAUNCHER_ACCRUEDCHARGES_TYPE VARCHAR(1),
/** 发起人管理费计提金额 **/
LAUNCHER_ACCRUEDCHARGES_AMOUNT  DECIMAL(20,4),
/** 投资人管理费计提方式 **/
USER_ACCRUEDCHARGES_TYPE  VARCHAR(1),
/** 投资人管理费计提金额 **/
USER_ACCRUEDCHARGES_AMOUNT DECIMAL(20,4),
/** 创建时间 **/
CREATE_DATE VARCHAR(20),
/** 更新时间 **/
UPDATE_DATE VARCHAR(20),
/** 是否删除 0否1是 **/
IS_DEL VARCHAR(1),
PRIMARY KEY(INVT_PRODUCT_ID)
);
DROP TABLE T_INVEST_BASE;
/** 投资产品-基础产品关联表 **/
CREATE TABLE T_INVEST_BASE
(
-- 投资产品ID --
INVT_PRODUCT_ID INT,
-- 基础投资产品ID --
BASIC_PRODUCT_ID INT
);
/** 用户投资表 **/
DROP TABLE T_INVEST;
CREATE TABLE T_INVEST(
-- 用户投资ID
USER_INVEST_ID INT NOT NULL AUTO_INCREMENT,
-- 用户ID
USER_ID INT NOT NULL,
-- 投资产品ID
INVEST_PRDOUCT_ID INT NOT NULL,
-- 交易流水号
TRANSION_SEQ VARCHAR(50),
-- 交易状态
INVEST_STATUS VARCHAR(2),
-- 投资方式
INVEST_TYPE VARCHAR(2),
-- 产品费用总金额
ACOUNT_PRTFEEALL DECIMAL(20,4),
-- 保证金费用总金额
ACOUNT_BAILALL DECIMAL(20,4),
-- 投资管理费费用总金额
ACOUNT_CHARGEALL DECIMAL(20,4),
-- 总费用金额
AMOUNT_ALL DECIMAL(20,4),
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(USER_INVEST_ID)
);
/** 用户投资明细表 **/
DROP TABLE T_INVEST_DETAIL;
CREATE TABLE T_INVEST_DETAIL(
DETAIL_ID INT NOT NULL AUTO_INCREMENT,
-- 用户投资ID
USER_INVEST_ID  INT NOT NULL,
-- 投资产品ID
INVEST_PRDOUCT_ID INT NOT NULL,
-- 基础投资产品ID
BASIC_PRODUCT_ID INT NOT NULL,
-- 基础产品费用金额
ACOUNT_PRTFEE DECIMAL(20,4),
-- 基础产品保证金费用总金额
ACOUNT_BAIL DECIMAL(20,4),
-- 基础产品投资管理费费用总金额
ACOUNT_CHARGE DECIMAL(20,4),
-- 基础产品总费用金额
AMOUNT DECIMAL(20,4),
-- 开始号
START_NO VARCHAR(20),
-- 份数
SUB_CODE INT NOT NULL,
-- 投资状态
INVEST_STATUS VARCHAR(2),
-- 用户ID
USER_ID INT,
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(DETAIL_ID)
);

/** 用户投资号码库 **/
DROP TABLE T_INVEST_NOINFO;
CREATE TABLE T_INVEST_NOINFO(
NO_ID INT NOT NULL AUTO_INCREMENT,
-- 投资明细ID
DETAIL_ID INT,
-- 用户投资ID
USER_INVEST_ID  INT NOT NULL,
-- 投资产品ID
INVEST_PRDOUCT_ID INT NOT NULL,
-- 基础产品ID
BASIC_PRODUCT_ID INT NOT NULL,
-- 号码
INVEST_NO VARCHAR(20),
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(NO_ID)
);

/** 个人交易表 **/
DROP TABLE T_TRANSION;
CREATE TABLE T_TRANSION(
TRAN_ID INT NOT NULL AUTO_INCREMENT,
-- 交易流水号
TRANSION_SEQ VARCHAR(50),
-- 用户ID
USER_ID INT NOT NULL,
-- 交易类型
TRANSION_TYPE VARCHAR(2),
-- 收付方式
PAYMENT_TYPE VARCHAR(2),
-- 银行流水号
BANK_SEQ VARCHAR(50),
-- 金额
AMOUNT DECIMAL(20,4),
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(TRAN_ID)
);
/** 客户收益表 **/
DROP TABLE T_PRODUCT_SETTLE;
CREATE TABLE T_PRODUCT_SETTLE(
-- ID
USTL_ID INT NOT NULL  AUTO_INCREMENT,
-- 结算投资用户ID
INVEST_USER_ID INT NOT NULL,
-- 结算投资产品ID
INVT_PRODUCT_ID  INT NOT NULL ,
-- 结算流水号
TRANSION_SEQ VARCHAR(50),
-- 利润分配流水号
PROFIT_SEQ VARCHAR(50),
-- 用户投资应付利润
USER_INVEST_PROFITPAY DECIMAL(20,4),
-- 用户投资应收利润
USER_INVEST_PROFIT DECIMAL(20,4),
-- 用户中签数
USER_COUNT INT,
-- 用户投资保证金总额
USER_INVESTBAIL_ALL DECIMAL(20,4),
-- 用户投资管理费总额
USER_INVESTCHARGE_ALL DECIMAL(20,4),
-- 用户购买总额
USER_BUYCOUNT_ALL DECIMAL(20,4),
-- 用户总投资额
USER_INVESTCOUNT_ALL DECIMAL(20,4),
-- 用户总收入额
USER_RECIVECOUNT_ALL DECIMAL(20,4),
-- 总付款额
USER_PAYCOUNT_ALL DECIMAL(20,4),
-- 是否结账
SETTLE_FLAG VARCHAR(1),
-- 是否删除
DEL_FLAG VARCHAR(1),
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(USTL_ID)
);
/** 投资结算明细表 **/
DROP TABLE T_PRODUCT_SETTLEDETAIL;
CREATE TABLE T_PRODUCT_SETTLEDETAIL(
-- 结算ID
SETTLE_ID INT NOT NULL  AUTO_INCREMENT,
-- 结算流水号
TRANSION_SEQ VARCHAR(50),
-- 结算投资产品ID
INVT_PRODUCT_ID  INT NOT NULL,
-- 结算投资用户ID
INVEST_USER_ID INT NOT NULL,
-- 用户投资ID
USER_INVEST_ID INT  NOT NULL,
-- 结算基础产品ID
BASIC_PRODUCT_ID INT NOT NULL,
-- 投资明细ID
DETAIL_ID INT,
-- 产品买入价
PRICE_PRV DECIMAL(20,4),
-- 产品卖出价
PRICE_AFT DECIMAL(20,4),
-- 产品中签数
PRAISE_COUNT INT,
-- 产品投资保证金总额
PRODUCT_INVESTBAIL_ALL DECIMAL(20,4),
-- 产品投资管理费总额
PRODUCT_INVESTCHARGE_ALL DECIMAL(20,4),
--  产品购买总额
PRODUCT_BUYCOUNT_ALL DECIMAL(20,4),
-- 产品总投资额
PRODUCT_INVESTCOUNT_ALL DECIMAL(20,4),
--  产品总收入额
PRODUCT_RECIVECOUNT_ALL DECIMAL(20,4),
-- 用户投资结算前净利润
USER_INVEST_PROFIT DECIMAL(20,4),
-- 用户投资结算后净利润
USER_SETTLE_PROFIT DECIMAL(20,4),
-- 创建时间 
CREATE_DATE VARCHAR(20),
-- 更新时间 
UPDATE_DATE VARCHAR(20),
PRIMARY KEY(SETTLE_ID)
);
USE p2ptest;
/** 系统信息码表 **/
DROP TABLE T_MSG_INFO;
CREATE TABLE T_MSG_INFO
(
-- ID --
MSG_ID INT NOT NULL AUTO_INCREMENT,
-- 信息编码 一
MSG_CODE VARCHAR(20),
-- 信息类型 一
MSG_TYP VARCHAR(2),
-- 信息内容 --
MSG_COMMENT VARCHAR(50),
PRIMARY KEY(MSG_ID)
);


/**** 菜单表 *********/
DROP TABLE T_MENU;
CREATE TABLE T_MENU(
-- 菜单ID
MENU_ID INT NOT NULL AUTO_INCREMENT,
-- 菜单编码
MENU_CODE VARCHAR(20),
-- 菜单名称
MENU_NAME VARCHAR(100),
-- 菜单级别
MENU_ORDER VARCHAR(2),
-- 是否为根菜单
MENU_FLAG VARCHAR(1),
-- 是否需要权限控制
MENU_PRVGFLAG VARCHAR(1),
-- 父菜单ID
MENU_PARENTCODE VARCHAR(20),
-- 菜单连接地址
MENU_ACTION VARCHAR(100),
-- 菜单样式
MENU_ICONCLASS VARCHAR(100),
PRIMARY KEY(MENU_ID,MENU_CODE)
);
/**** 角色表 *********/
DROP TABLE T_ROLE;
CREATE TABLE T_ROLE(
-- 角色ID
ROLE_ID INT NOT NULL AUTO_INCREMENT,
-- 角色代码
ROLE_CODE VARCHAR(20),
-- 角色名称
ROLE_NAME VARCHAR(100),
-- 角色描述
ROLE_REMARK VARCHAR(200),
-- 角色菜单集合串
MENU_CODEARRAY VARCHAR(500),
PRIMARY KEY(ROLE_ID)
);
/**** 角色菜单关联表 *********/
DROP TABLE T_ROLE_MENU;
CREATE TABLE T_ROLE_MENU(
-- 序号
ID INT NOT NULL AUTO_INCREMENT,
-- 角色代码
ROLE_CODE VARCHAR(20),
-- 角色菜单编码
MENU_CODE VARCHAR(20),
PRIMARY KEY(ID)
);

/**** 用户权限表 *********/
DROP TABLE T_USER_PRVG;
CREATE TABLE T_USER_PRVG(
-- 权限ID
PRVG_ID INT NOT NULL AUTO_INCREMENT,
-- 用户ID
USER_ID  INT NOT NULL,
-- 角色ID
ROLE_CODE VARCHAR(20),
PRIMARY KEY(PRVG_ID)
);