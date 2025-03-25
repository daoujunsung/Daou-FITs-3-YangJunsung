#include "trade.h"
#include "db.h"
#include <stdio.h>
#include <stdlib.h>

void add_trade(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, Trade trade) {
    printf("거래 추가: 고객=%s, 종목=%s, 유형=%d, 수량=%d, 가격=%.2lf\n", trade.customer_name, trade.stock_name, trade.trade_type, trade.amount, trade.price);
}

void update_trade(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, int id, int amount, double price) {
    printf("거래 수정: ID=%d, 수량=%d, 가격=%.2lf\n", id, amount, price);
}

void save_to_csv(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* filename) {
    printf("CSV 저장: %s\n", filename);
}

void load_from_csv(OCIEnv* envhp, OCISvcCtx* svchp, OCIError* errhp, const char* filename) {
    printf("CSV 불러오기: %s\n", filename);
}