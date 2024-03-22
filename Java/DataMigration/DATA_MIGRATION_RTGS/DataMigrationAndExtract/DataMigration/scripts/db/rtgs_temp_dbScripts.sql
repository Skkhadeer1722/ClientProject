use rtgs_nets_mig_new;

-- 1. ROLLBACK SCHEMA PATCH FOR {account_temp}
-- NOTE:
DROP table IF EXISTS account_temp;
CREATE TABLE `account_temp` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account Id',
  `account_number` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account number',
  `description` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account description',
  `member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `account_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account type',
  `main_account_indicator` tinyint(1) DEFAULT NULL COMMENT 'main account indicator',
  `default_main_account` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'default main account',
  `statement_delivery_bic` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement delivery BIC',
  `account_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'account status',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'currency code',
  `end_of_day_statement` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'statement to be sent by end of the day',
  `payer_reference` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Payer Reference No for the Target Transaction',
  `related_reference` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Related Reference No for the Target Transaction',
  `counterparty_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'counterparty code',
  `value_date` int DEFAULT '0' COMMENT 'value date of this record',
  `activation_date` date DEFAULT NULL COMMENT 'effective date for the whole any changes made in Sub Account Management except Account Status field',
  `gl_category` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL category, possible value: GLBNK, GLAGD, GLFCB, GLFIN, GLIFI, GLOTH, BLANK',
  `cost_centre` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'cost centre',
  `gl_account_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GL account number',
  `statement_interval` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'No' COMMENT 'interval at which statements are generated, possible value: Daily, Monthly, No',
  `action` varchar(6) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `compliance_calculation` tinyint(1) DEFAULT NULL COMMENT 'Included for Compliance Calculation',
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {account_position_temp}
-- NOTE:
DROP table IF EXISTS account_position_temp;
CREATE TABLE `account_position_temp` (
  `id` varchar(36) NOT NULL,
  `account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'account id',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'currency code',
  `member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'member id',
  `current_account_balance` decimal(23,5) NOT NULL COMMENT 'current or closing balance of the account.',
  `opening_account_balance` decimal(23,5) NOT NULL COMMENT 'opening balance of the account for the selected value date',
  `available_balance` decimal(23,5) NOT NULL DEFAULT '0.00000' COMMENT 'available balance',
  `queue_count` int DEFAULT '0' COMMENT 'counts of transactions those were in the queue',
  `queue_amount` decimal(23,5) DEFAULT NULL COMMENT 'amounts of transactions those were in the queue',
  `settled_payments_count` int DEFAULT '0' COMMENT 'counts of payments due to transactions those were settled today.',
  `settled_payments_amount` decimal(23,5) DEFAULT NULL COMMENT 'amounts of payments due to transactions those were settled today.',
  `settled_receipts_count` int DEFAULT '0' COMMENT 'counts of receipts due to transactions those were settled today',
  `settled_receipts_amount` decimal(23,5) DEFAULT NULL COMMENT 'amounts of receipts due to transactions those were settled today',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_cost_centre_temp}
-- NOTE:
DROP table IF EXISTS cbm_cost_centre_temp;
CREATE TABLE `cbm_cost_centre_temp` (
  `id` varchar(36) NOT NULL,
  `cost_centre` varchar(6) NOT NULL COMMENT 'Cost centre',
  `description` varchar(50) NOT NULL COMMENT 'Cost centre description',
  `created_date` timestamp NOT NULL COMMENT 'Created date',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Modified date',
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_deposit_rate_temp}
-- NOTE:
DROP table IF EXISTS cbm_deposit_rate_temp;
CREATE TABLE `cbm_deposit_rate_temp` (
  `id` varchar(36) NOT NULL,
  `tenor_period` int NOT NULL COMMENT 'Standing facility tenor period in calendar days',
  `deposit_rate` decimal(8,5) NOT NULL COMMENT 'Deposit rate% for the standing facility tenor period',
  `facility_id` varchar(36) NOT NULL COMMENT 'Facility id',
  `action` varchar(30) DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `modified_by` varchar(36) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `approved_by` varchar(36) DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NOT NULL,
  `effective_date` timestamp NOT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` int DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_gl_account_temp}
-- NOTE:
DROP table IF EXISTS cbm_gl_account_temp;
CREATE TABLE `cbm_gl_account_temp` (
  `id` varchar(36) NOT NULL,
  `gl_account_indicator` varchar(14) NOT NULL COMMENT 'GL account indicator',
  `gl_account` varchar(50) NOT NULL COMMENT 'GL account',
  `gl_account_description` varchar(50) NOT NULL COMMENT 'GL account description',
  `created_date` timestamp NOT NULL COMMENT 'Created date',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Modified date',
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_liabilities_base_detail_temp}
-- NOTE:
DROP table IF EXISTS cbm_liabilities_base_detail_temp;
CREATE TABLE `cbm_liabilities_base_detail_temp` (
  `id` varchar(50) NOT NULL COMMENT 'Liabilities base detail id',
  `member_id` varchar(36) NOT NULL COMMENT 'Member id',
  `start_date` date NOT NULL COMMENT 'Start date of maintenance period',
  `end_date` date NOT NULL COMMENT 'End date of maintenance period',
  `ql_lb` decimal(23,5) DEFAULT NULL COMMENT 'QL/LB submission amount',
  `remarks` varchar(300) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `live_table_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_liabilities_base_temp}
-- NOTE:
DROP table IF EXISTS cbm_liabilities_base_temp;
CREATE TABLE `cbm_liabilities_base_temp` (
  `id` varchar(36) NOT NULL,
  `member_id` varchar(36) DEFAULT NULL COMMENT 'Member id',
  `sector_id` varchar(4) DEFAULT NULL COMMENT 'Sector id',
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code',
  `mcb_id` varchar(10) DEFAULT NULL COMMENT 'MCB id',
  `ql_lb_type` varchar(3) NOT NULL COMMENT 'QL / LB items',
  `action` varchar(30) DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `modified_by` varchar(36) DEFAULT NULL,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `approved_by` varchar(36) DEFAULT NULL,
  `approved_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NOT NULL,
  `effective_date` timestamp NOT NULL,
  `approval_remark` varchar(140) DEFAULT NULL,
  `workflow_status_id` int DEFAULT NULL,
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {cbm_sora_rate_temp}
-- NOTE:
DROP table IF EXISTS cbm_sora_rate_temp;
CREATE TABLE `cbm_sora_rate_temp` (
  `id` varchar(36) NOT NULL,
  `reference_rate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Reference Rate',
  `publication_date` int NOT NULL COMMENT 'Publication Date',
  `value_date` int NOT NULL COMMENT 'Value Date',
  `rate` decimal(13,10) DEFAULT NULL COMMENT 'Rate',
  `action` varchar(6) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_floating-rate_This table is used to save the calculated cash rate.';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {member_temp}
-- NOTE:
DROP table IF EXISTS member_temp;
CREATE TABLE `member_temp` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PRIMARY KEY - Id',
  `member_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SENSITIVE, member code',
  `swift_bic` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SENSITIVE, member’s SWIFT BIC',
  `swift_member` tinyint(1) NOT NULL COMMENT 'swift member',
  `bank_code` smallint NOT NULL COMMENT 'SENSITIVE, unique bank code',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member name',
  `short_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s short name',
  `member_status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s status',
  `member_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member type',
  `sector_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'sector ID',
  `mcb_id` varchar(10) NOT NULL,
  `uen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'SENSITIVE, UEN number',
  `lei` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'LEI number',
  `member_classification` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'member’s classification',
  `exempted_from_billing` tinyint(1) DEFAULT NULL COMMENT 'the indication whether the member is exempted from the billing',
  `exempted_from_system_limit` tinyint(1) DEFAULT NULL COMMENT 'the indication whether the member is exempted from system limit',
  `location` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'location',
  `activation_date` timestamp NULL DEFAULT NULL COMMENT 'activation date of the member',
  `action` varchar(6) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(36) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `fi_group` varchar(1) NOT NULL,
  `billing_profile_id` varchar(36) DEFAULT NULL,
  `mcb_intraday` decimal(8,5) NOT NULL,
  `mcb_eod_minimum` decimal(8,5) NOT NULL,
  `mcb_eod_maximum` decimal(8,5) NOT NULL,
  `mcb_average` decimal(8,5) NOT NULL,
  `tax_profile_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Its tax profile for billing',
  `verified_by` varchar(36) DEFAULT NULL,
  `verified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END