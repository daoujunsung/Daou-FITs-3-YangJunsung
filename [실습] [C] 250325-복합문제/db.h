#pragma once
#ifndef DB_H
#define DB_H
#include <oci.h>

void check_error(OCIError* errhp);

void connect_db(OCIEnv** envhp, OCIError** errhp, OCISvcCtx** svchp, OCISession** usrhp, OCIServer** srvhp,
    const char* username, const char* password, const char* dbname);

void select_all_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp);

void select_data_by_customer(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* customer_name);

void insert_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp,
    int id, const char* customer_name, const char* stock_name, int trade_type, int amount, int price);

void update_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id, int amount, int price);

void delete_data(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id);

void disconnect_db(OCIEnv* envhp, OCIError* errhp, OCISvcCtx* svchp, OCISession* usrhp, OCIServer* srvhp);

#endif