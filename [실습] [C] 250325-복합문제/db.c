#include "db.h"
#include <stdio.h>
#include <stdlib.h>
#include <oci.h>

void check_error(OCIError* errhp) {
    text errbuf[512];
    sb4 errcode = 0;
    OCIErrorGet(errhp, 1, NULL, &errcode, errbuf, sizeof(errbuf), OCI_HTYPE_ERROR);
    printf("Oracle Error: %s\n", errbuf);
}

void connect_db(OCIEnv** envhp, OCIError** errhp, OCISvcCtx** svchp, OCISession** usrhp, OCIServer** srvhp,
    const char* username, const char* password, const char* dbname) {
    OCIEnvCreate(envhp, OCI_DEFAULT, NULL, NULL, NULL, NULL, 0, NULL);
    OCIHandleAlloc(*envhp, (void**)errhp, OCI_HTYPE_ERROR, 0, NULL);
    OCIHandleAlloc(*envhp, (void**)srvhp, OCI_HTYPE_SERVER, 0, NULL);
    OCIServerAttach(*srvhp, *errhp, (OraText*)dbname, strlen(dbname), OCI_DEFAULT);
    OCIHandleAlloc(*envhp, (void**)svchp, OCI_HTYPE_SVCCTX, 0, NULL);
    OCIAttrSet(*svchp, OCI_HTYPE_SVCCTX, *srvhp, 0, OCI_ATTR_SERVER, *errhp);
    OCIHandleAlloc(*envhp, (void**)usrhp, OCI_HTYPE_SESSION, 0, NULL);
    OCIAttrSet(*usrhp, OCI_HTYPE_SESSION, (void*)username, strlen(username), OCI_ATTR_USERNAME, *errhp);
    OCIAttrSet(*usrhp, OCI_HTYPE_SESSION, (void*)password, strlen(password), OCI_ATTR_PASSWORD, *errhp);
    OCISessionBegin(*svchp, *errhp, *usrhp, OCI_CRED_RDBMS, OCI_DEFAULT);
    OCIAttrSet(*svchp, OCI_HTYPE_SVCCTX, *usrhp, 0, OCI_ATTR_SESSION, *errhp);
    printf("Oracle DB 연결 성공!\n");
}

void select_all_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp) {
    OCIStmt* stmthp;
    OCIDefine* def1 = NULL, * def2 = NULL, * def3 = NULL, * def4 = NULL, * def5 = NULL, * def6 = NULL;
    int id, trade_type, amount;
    char customer_name[50], stock_name[50];
    double price;
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    char* select_sql = "SELECT ID, CUSTOMER_NAME, STOCK_NAME, TRADE_TYPE, AMOUNT, PRICE FROM stock_trade_table";
    OCIStmtPrepare(stmthp, errhp, (text*)select_sql, strlen(select_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIStmtExecute(svchp, stmthp, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def2, errhp, 2, customer_name, sizeof(customer_name), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def3, errhp, 3, stock_name, sizeof(stock_name), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def1, errhp, 4, &trade_type, sizeof(trade_type), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def1, errhp, 5, &amount, sizeof(amount), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def1, errhp, 6, &price, sizeof(price), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);

    printf("[모든 거래 내역]");

    while (OCIStmtFetch2(stmthp, errhp, 1, OCI_DEFAULT, 0, OCI_DEFAULT) == OCI_SUCCESS) {
        printf("ID: %d, 고객: %s, 종목: %s, 유형: %s, 수량: %d, 가격: %.2lf원\n",
            id, customer_name, stock_name, (trade_type == 0) ? "매수" : "매도", amount, price);
    }
}

void select_data_by_customer(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* customer_name) {
    OCIStmt* stmthp;
    OCIDefine* def1 = NULL, * def2 = NULL, * def3 = NULL, * def4 = NULL, * def5 = NULL, * def6 = NULL;
    int id=0, trade_type=0, amount=0;
    char stock_name[50];
    double price=0;
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    char* select_sql = "SELECT ID, STOCK_NAME, TRADE_TYPE, AMOUNT, PRICE FROM stock_trade_table WHERE CUSTOMER_NAME = :1";
    OCIStmtPrepare(stmthp, errhp, (text*)select_sql, strlen(select_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIBindByPos(stmthp, &def1, errhp, 1, (void*)customer_name, strlen(customer_name) + 1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIStmtExecute(svchp, stmthp, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);

    printf("[%s님의 거래 내역]\n", customer_name);
    while (OCIStmtFetch2(stmthp, errhp, 1, OCI_DEFAULT, 0, OCI_DEFAULT) == OCI_SUCCESS) {
        printf("ID: %d, 종목: %s, 유형: %s, 수량: %d, 가격: %.2lf원\n",
            id, stock_name, (trade_type == 0) ? "매수" : "매도", amount, price);
    }
}

void insert_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp,
    int id, const char* customer_name, const char* stock_name, int trade_type, int amount, int price) {
    OCIStmt* stmthp;
    OCIBind* bnd1 = NULL, * bnd2 = NULL, * bnd3 = NULL, * bnd4 = NULL, * bnd5 = NULL, * bnd6 = NULL;
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    char* insert_sql = "INSERT INTO stock_trade_table (ID, CUSTOMER_NAME, STOCK_NAME, TRADE_TYPE, AMOUNT, PRICE) VALUES (:1, :2, :3, :4, :5, :6)";
    OCIStmtPrepare(stmthp, errhp, (text*)insert_sql, strlen(insert_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd2, errhp, 2, (void*)customer_name, strlen(customer_name) + 1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd3, errhp, 3, (void*)stock_name, strlen(stock_name) + 1, SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd4, errhp, 4, &trade_type, sizeof(trade_type), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd5, errhp, 5, &amount, sizeof(amount), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd6, errhp, 6, &price, sizeof(price), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("데이터 삽입 완료!\n");
    }
}

void update_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id, int amount, int price) {
    OCIStmt* stmthp;
    OCIBind* bnd1 = NULL, * bnd2 = NULL, * bnd3 = NULL;
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    char* update_sql = "UPDATE stock_trade_table SET amount = :1, price = :2 WHERE id = :3";
    OCIStmtPrepare(stmthp, errhp, (text*)update_sql, strlen(update_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("데이터 삽입 완료!\n");
    }
}

void delete_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id) {
    OCIStmt* stmthp;
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    char* delete_sql = "DELETE FROM stock_trade_table WHERE ID = :1";
    OCIStmtPrepare(stmthp, errhp, (text*)delete_sql, strlen(delete_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS);
    printf("ID %d 데이터 삭제 완료!\n", id);
}

void disconnect_db(OCIEnv* envhp, OCIError* errhp, OCISvcCtx* svchp, OCISession* usrhp, OCIServer* srvhp) {
    OCISessionEnd(svchp, errhp, usrhp, OCI_DEFAULT);
    OCIServerDetach(srvhp, errhp, OCI_DEFAULT);
    OCIHandleFree(svchp, OCI_HTYPE_SVCCTX);
    OCIHandleFree(usrhp, OCI_HTYPE_SESSION);
    OCIHandleFree(srvhp, OCI_HTYPE_SERVER);
    OCIHandleFree(errhp, OCI_HTYPE_ERROR);
    OCIHandleFree(envhp, OCI_HTYPE_ENV);
    printf("Oracle DB 연결 해제 완료!\n");
}