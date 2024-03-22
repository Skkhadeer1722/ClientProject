use rtgs_nets_mig_new;

-- 1. ROLLBACK SCHEMA PATCH FOR {account_source}
-- NOTE:
DROP table IF EXISTS account_source;
CREATE TABLE `account_source` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account Id',
  `account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account number',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account description',
  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `account_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account type',
  `main_account_indicator` varchar(50) DEFAULT NULL COMMENT 'main account indicator',
  `default_main_account` varchar(50) DEFAULT NULL COMMENT 'default main account',
  `statement_delivery_bic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement delivery BIC',
  `account_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account status',
  `currency_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code',
  `end_of_day_statement` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement to be sent by end of the day',
  `payer_reference` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Payer Reference No for the Target Transaction',
  `related_reference` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Related Reference No for the Target Transaction',
  `counterparty_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'counterparty code',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'value date of this record',
  `activation_date` varchar(50) DEFAULT NULL COMMENT 'effective date for the whole any changes made in Sub Account Management except Account Status field',
  `gl_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL category, possible value: GLBNK, GLAGD, GLFCB, GLFIN, GLIFI, GLOTH, BLANK',
  `cost_centre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'cost centre',
  `gl_account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL account number',
  `statement_interval` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'interval at which statements are generated, possible value: Daily, Monthly, No',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `compliance_calculation` varchar(50) DEFAULT NULL COMMENT 'Included for Compliance Calculation',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {account_position_source}
-- NOTE:
DROP table IF EXISTS account_position_source;
CREATE TABLE `account_position_source` (
  `id` varchar(36) DEFAULT NULL,
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account id',
  `currency_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code',
  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `current_account_balance` varchar(50) DEFAULT NULL COMMENT 'current or closing balance of the account.',
  `opening_account_balance` varchar(50) DEFAULT NULL COMMENT 'opening balance of the account for the selected value date',
  `available_balance` varchar(50) DEFAULT '0.00000' COMMENT 'available balance',
  `queue_count` varchar(50) DEFAULT NULL COMMENT 'counts of transactions those were in the queue',
  `queue_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of transactions those were in the queue',
  `settled_payments_count` varchar(50) DEFAULT NULL COMMENT 'counts of payments due to transactions those were settled today.',
  `settled_payments_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of payments due to transactions those were settled today.',
  `settled_receipts_count` varchar(50) DEFAULT NULL COMMENT 'counts of receipts due to transactions those were settled today',
  `settled_receipts_amount` varchar(50) DEFAULT NULL COMMENT 'amounts of receipts due to transactions those were settled today',
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(200) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_cost_centre_source}
-- NOTE:
DROP table IF EXISTS cbm_cost_centre_source;
CREATE TABLE `cbm_cost_centre_source` (
  `id` varchar(36) DEFAULT NULL,
  `cost_centre` varchar(30) DEFAULT NULL COMMENT 'Cost centre',
  `description` varchar(50) DEFAULT NULL COMMENT 'Cost centre description',
  `created_date` varchar(30) DEFAULT NULL COMMENT 'Created date',
  `modified_date` varchar(30) DEFAULT NULL COMMENT 'Modified date',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(200) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_deposit_rate_source}
-- NOTE:
DROP table IF EXISTS cbm_deposit_rate_source;
CREATE TABLE `cbm_deposit_rate_source` (
  `id` varchar(36) DEFAULT NULL,
  `tenor_period` varchar(50) DEFAULT NULL COMMENT 'Standing facility tenor period in calendar days',
  `deposit_rate` varchar(50) DEFAULT NULL COMMENT 'Deposit rate% for the standing facility tenor period',
  `facility_id` varchar(50) DEFAULT NULL COMMENT 'Facility id',
  `action` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `approved_by` varchar(50) DEFAULT NULL,
  `approved_date` varchar(50) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `effective_date` varchar(50) DEFAULT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` int DEFAULT NULL,
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_gl_account_source}
-- NOTE:
DROP table IF EXISTS cbm_gl_account_source;
CREATE TABLE `cbm_gl_account_source` (
  `id` varchar(36) DEFAULT NULL,
  `gl_account_indicator` varchar(50) DEFAULT NULL COMMENT 'GL account indicator',
  `gl_account` varchar(50) DEFAULT NULL COMMENT 'GL account',
  `gl_account_description` varchar(50) DEFAULT NULL COMMENT 'GL account description',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Modified date',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(200) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_liabilities_base_detail_source}
-- NOTE:
DROP table IF EXISTS cbm_liabilities_base_detail_source;
CREATE TABLE `cbm_liabilities_base_detail_source` (
  `id` varchar(50) DEFAULT NULL COMMENT 'Liabilities base detail id',
  `member_id` varchar(50) DEFAULT NULL COMMENT 'Member id',
  `start_date` varchar(50) DEFAULT NULL COMMENT 'Start date of maintenance period',
  `end_date` varchar(50) DEFAULT NULL COMMENT 'End date of maintenance period',
  `ql_lb` varchar(50) DEFAULT NULL COMMENT 'QL/LB submission amount',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_liabilities_base_source}
-- NOTE:
DROP table IF EXISTS cbm_liabilities_base_source;
CREATE TABLE `cbm_liabilities_base_source` (
  `id` varchar(36) DEFAULT NULL,
  `member_id` varchar(36) DEFAULT NULL COMMENT 'Member id',
  `sector_id` varchar(50) DEFAULT NULL COMMENT 'Sector id',
  `currency_code` varchar(50) DEFAULT NULL COMMENT 'Currency code',
  `mcb_id` varchar(50) DEFAULT NULL COMMENT 'MCB id',
  `ql_lb_type` varchar(50) DEFAULT NULL COMMENT 'QL / LB items',
  `action` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `approved_by` varchar(50) DEFAULT NULL,
  `approved_date` varchar(50) DEFAULT NULL,
  `created_date` varchar(50) DEFAULT NULL,
  `effective_date` varchar(50) DEFAULT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` varchar(50) DEFAULT NULL,
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_sora_rate_source}
-- NOTE:
DROP table IF EXISTS cbm_sora_rate_source;
CREATE TABLE `cbm_sora_rate_source` (
  `id` varchar(50) DEFAULT NULL,
  `reference_rate` varchar(50) DEFAULT NULL COMMENT 'Reference Rate',
  `publication_date` varchar(50) DEFAULT NULL COMMENT 'Publication Date',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'Value Date',
  `rate` varchar(50) DEFAULT NULL COMMENT 'Rate',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_floating-rate_This table is used to save the calculated cash rate.';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {member_source}
-- NOTE:
DROP table IF EXISTS member_source;
CREATE TABLE `member_source` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PRIMARY KEY - Id',
  `member_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SENSITIVE, member code',
  `swift_bic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SENSITIVE, member’s SWIFT BIC',
  `swift_member` varchar(50) DEFAULT NULL COMMENT 'swift member',
  `bank_code` varchar(50) DEFAULT NULL COMMENT 'SENSITIVE, unique bank code',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member name',
  `short_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member’s short name',
  `member_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member’s status',
  `member_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member type',
  `sector_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sector ID',
  `mcb_id` varchar(50) DEFAULT NULL,
  `uen` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SENSITIVE, UEN number',
  `lei` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'LEI number',
  `member_classification` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member’s classification',
  `exempted_from_billing` varchar(50) DEFAULT NULL COMMENT 'the indication whether the member is exempted from the billing',
  `exempted_from_system_limit` varchar(50) DEFAULT NULL COMMENT 'the indication whether the member is exempted from system limit',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'location',
  `activation_date` varchar(50) DEFAULT NULL COMMENT 'activation date of the member',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `fi_group` varchar(50) DEFAULT NULL,
  `billing_profile_id` varchar(36) DEFAULT NULL,
  `mcb_intraday` varchar(50) DEFAULT NULL,
  `mcb_eod_minimum` varchar(50) DEFAULT NULL,
  `mcb_eod_maximum` varchar(50) DEFAULT NULL,
  `mcb_average` varchar(50) DEFAULT NULL,
  `tax_profile_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Its tax profile for billing',
  `verified_by` varchar(36) DEFAULT NULL,
  `verified_date` varchar(50) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END