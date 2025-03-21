#include <stdio.h>
#include <stdlib.h>
#include <oci.h>

void check_error(OCIError* errhp) {
    text errbuf[512];
    sb4 errcode = 0;
    OCIErrorGet(errhp, 1, NULL, &errcode, errbuf, sizeof(errbuf), OCI_HTYPE_ERROR);
    printf("Oracle Error: %s\n", errbuf);
}

// 데이터 조회 함수 (SELECT)
void query_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, OCIStmt* stmthp) {
    sword status;
    int id;
    char name[50];
    char phone[50];
    char birthDate[50];
    OCIDefine* def1 = NULL, * def2 = NULL, * def3 = NULL, * def4 = NULL;

    char* select_sql = "SELECT CUSTOMER_ID, NAME, PHONE, BIRTH_DATE FROM test_customers";
    OCIStmtPrepare(stmthp, errhp, (text*)select_sql, strlen(select_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);
    OCIStmtExecute(svchp, stmthp, errhp, 0, 0, NULL, NULL, OCI_DEFAULT);

    OCIDefineByPos(stmthp, &def1, errhp, 1, &id, sizeof(id), SQLT_INT, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def2, errhp, 2, name, sizeof(name), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def3, errhp, 3, phone, sizeof(phone), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);
    OCIDefineByPos(stmthp, &def4, errhp, 4, birthDate, sizeof(birthDate), SQLT_STR, NULL, NULL, NULL, OCI_DEFAULT);

    printf("테이블 조회 결과:\n");
    printf("---------------------------------------------------------------------\n");
    printf("| ID | NAME                | PHONE              | BIRTH DATE         |\n");
    printf("---------------------------------------------------------------------\n");

    while ((status = OCIStmtFetch2(stmthp, errhp, 1, OCI_DEFAULT, 0, OCI_DEFAULT)) == OCI_SUCCESS || status == OCI_SUCCESS_WITH_INFO) {
        printf("| %4d | %-20s | %-20s | %-20s |\n", id, name, phone, birthDate);
    }
    printf("---------------------------------------------------------------------\n");
}

int main() {
    OCIEnv* envhp;
    OCIError* errhp;
    OCISvcCtx* svchp;
    OCISession* usrhp;
    OCIServer* srvhp;
    OCIStmt* stmthp;
    OCIBind* bnd1 = NULL, * bnd2 = NULL, * bnd3 = NULL, * bnd4 = NULL;
    sword status;

    // DB 로그인 정보
    char* username = "C##DEV";
    char* password = "4587";
    char* dbname = "localhost:1521/xe"; // Oracle 서비스 이름

    // 환경 핸들 초기화
    OCIEnvCreate(&envhp, OCI_DEFAULT, NULL, NULL, NULL, NULL, 0, NULL);
    OCIHandleAlloc(envhp, (void**)&errhp, OCI_HTYPE_ERROR, 0, NULL);
    OCIHandleAlloc(envhp, (void**)&srvhp, OCI_HTYPE_SERVER, 0, NULL);
    OCIServerAttach(srvhp, errhp, (OraText*)dbname, strlen(dbname), OCI_DEFAULT);
    OCIHandleAlloc(envhp, (void**)&svchp, OCI_HTYPE_SVCCTX, 0, NULL);
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, srvhp, 0, OCI_ATTR_SERVER, errhp);
    OCIHandleAlloc(envhp, (void**)&usrhp, OCI_HTYPE_SESSION, 0, NULL);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, username, strlen(username), OCI_ATTR_USERNAME, errhp);
    OCIAttrSet(usrhp, OCI_HTYPE_SESSION, password, strlen(password), OCI_ATTR_PASSWORD, errhp);
    OCISessionBegin(svchp, errhp, usrhp, OCI_CRED_RDBMS, OCI_DEFAULT);
    OCIAttrSet(svchp, OCI_HTYPE_SVCCTX, usrhp, 0, OCI_ATTR_SESSION, errhp);
    printf("Oracle DB 연결 성공!\n");

    // 데이터 삽입 (INSERT)
    char* insert_sql = "INSERT INTO test_customers (CUSTOMER_ID, NAME, PHONE, BIRTH_DATE) VALUES (:1, :2, :3, :4)";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)insert_sql, strlen(insert_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    int insert_id = 3;
    char insert_name[50] = "김점순";
    char insert_phone[50] = "010-2222-3333";
    char insert_birthDate[50] = "1990-01-01";

    // 바인딩 변수 설정 (INSERT)
    OCIBindByPos(stmthp, &bnd1, errhp, 1, &insert_id, sizeof(insert_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd2, errhp, 2, insert_name, sizeof(insert_name), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd3, errhp, 3, insert_phone, sizeof(insert_phone), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd4, errhp, 4, insert_birthDate, sizeof(insert_birthDate), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("데이터 삽입 완료!\n");
    }

    // 데이터 조회 (SELECT)
    query_data(envhp, svchp, errhp, stmthp);

    // 데이터 수정 (UPDATE)
    char* update_sql = "UPDATE test_customers SET NAME = :1 WHERE CUSTOMER_ID = :2";
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)update_sql, strlen(update_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    char updated_name[50] = "박막례"; // 수정할 이름
    int update_id = 3; // 수정할 ID

    // 바인딩 변수 설정 (UPDATE)
    OCIBindByPos(stmthp, &bnd1, errhp, 1, updated_name, sizeof(updated_name), SQLT_STR, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);
    OCIBindByPos(stmthp, &bnd2, errhp, 2, &update_id, sizeof(update_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("데이터 수정 완료!\n");
    }

    // 데이터 조회 (SELECT) 다시
    query_data(envhp, svchp, errhp, stmthp);

    // 데이터 삭제 (DELETE)
    char* delete_sql = "DELETE FROM test_customers WHERE CUSTOMER_ID = :1"; // ID를 기준으로 삭제
    OCIHandleAlloc(envhp, (void**)&stmthp, OCI_HTYPE_STMT, 0, NULL);
    OCIStmtPrepare(stmthp, errhp, (text*)delete_sql, strlen(delete_sql), OCI_NTV_SYNTAX, OCI_DEFAULT);

    int delete_id = 3; // 삭제할 ID 값
    // 바인딩 변수 설정 (DELETE)
    OCIBindByPos(stmthp, &bnd1, errhp, 1, &delete_id, sizeof(delete_id), SQLT_INT, NULL, NULL, NULL, 0, NULL, OCI_DEFAULT);

    if (OCIStmtExecute(svchp, stmthp, errhp, 1, 0, NULL, NULL, OCI_COMMIT_ON_SUCCESS) != OCI_SUCCESS) {
        check_error(errhp);
    }
    else {
        printf("데이터 삭제 완료!\n");
    }

    // 데이터 조회 (SELECT) 마지막
    query_data(envhp, svchp, errhp, stmthp);

}
