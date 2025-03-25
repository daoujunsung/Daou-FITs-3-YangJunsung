#define _CRT_SECURE_NO_WARNINGS

#include "db.h"
#include "trade.h"
#include <stdio.h>
#include <stdlib.h>

int main() {
    OCIEnv* envhp = NULL;
    OCIError* errhp = NULL;
    OCISvcCtx* svchp = NULL;
    OCISession* usrhp = NULL;
    OCIServer* srvhp = NULL;
    char* username = "C##DEV";
    char* password = "4587";
    char* dbname = "localhost:1521/xe";
    connect_db(&envhp, &errhp, &svchp, &usrhp, &srvhp, username, password, dbname);

    int choice;
    while (1) {
        printf("1. 모든 거래 내역 조회\n2. 특정 고객 거래 내역 조회\n3. 거래 추가\n4. 거래 수정\n5. 거래 삭제\n6. CSV 저장\n7. CSV 불러오기\n0. 종료\n선택: ");
        scanf("%d", &choice);

        if (choice == 0) break;
        switch (choice) {
        case 1:
            select_all_data(envhp, svchp, errhp);
            break;
        case 2: {
            char name[MAX_NAME_LEN];
            printf("고객 이름: ");
            scanf("%s", name);
            select_data_by_customer(envhp, svchp, errhp, name);
            break;
        }
        case 3: {
            Trade t;
            printf("고객 이름: "); scanf("%s", t.customer_name);
            printf("종목 이름: "); scanf("%s", t.stock_name);
            printf("거래 유형 (0: 매수, 1: 매도): "); scanf("%d", &t.trade_type);
            printf("수량: "); scanf("%d", &t.amount);
            printf("가격: "); scanf("%lf", &t.price);
            add_trade(envhp, svchp, errhp, t);
            break;
        }
        case 4: {
            int id, amount;
            double price;
            printf("거래 ID: "); scanf("%d", &id);
            printf("수량: "); scanf("%d", &amount);
            printf("가격: "); scanf("%lf", &price);
            update_trade(envhp, svchp, errhp, id, amount, price);
            break;
        }
        case 5: {
            int id;
            printf("삭제할 거래 ID: "); scanf("%d", &id);
            delete_data(envhp, svchp, errhp, id);
            break;
        }
        case 6:
            save_to_csv(envhp, svchp, errhp, "trades.csv");
            break;
        case 7:
            load_from_csv(envhp, svchp, errhp, "trades.csv");
            break;
        default:
            printf("잘못된 선택입니다.\n");
        }
    }

    disconnect_db(envhp, errhp, svchp, usrhp, srvhp);
    return 0;
}