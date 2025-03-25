#pragma once

#ifndef TRADE_H
#define TRADE_H
#include <oci.h>
#define MAX_NAME_LEN 50

typedef struct {
    int id;
    char customer_name[MAX_NAME_LEN];
    char stock_name[MAX_NAME_LEN];
    int trade_type; // 0: 매수, 1: 매도
    int amount;
    double price;
} Trade;

void add_trade(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, Trade trade);
void update_trade(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id, int amount, double price);
void save_to_csv(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* filename);
void load_from_csv(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* filename);

#endif