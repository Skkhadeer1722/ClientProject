use sss_nets_mig;

-- 1. ROLLBACK SCHEMA PATCH FOR {account_temp}
-- NOTE:
DROP table IF EXISTS account_temp;
CREATE TABLE `account_temp` (
  `id` varchar(36) NOT NULL COMMENT 'Internal UUID',
  `member_id` varchar(36) NOT NULL COMMENT 'Internal Member ID (UUID)',
  `account_id` varchar(35) NOT NULL COMMENT 'Account ID - Unique to member',
  `description` varchar(35) NOT NULL COMMENT 'Account description',
  `custody_account_type_id` varchar(36) NOT NULL COMMENT 'Internal Custody Account Type ID (UUID)',
  `corporate_investor` varchar(20) DEFAULT NULL COMMENT 'LEI/UEN of the company',
  `account_status` varchar(3) NOT NULL COMMENT 'Account status - Pending Activation, Active, Suspended, Closed\nDefaults to ''Pending Activation'' for new account\n',
  `tax_profile_id` varchar(36) DEFAULT NULL COMMENT 'Tax Profile Id (UUID)',
  `statement_delivery_bic` varchar(11) DEFAULT NULL,
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_account_This table used to save the SSS accounts';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {allotment_temp}
-- NOTE:
DROP table IF EXISTS allotment_temp;
CREATE TABLE `allotment_temp` (
  `id` varchar(36) NOT NULL COMMENT 'Primary key (UUID)',
  `securities_code` varchar(12) DEFAULT NULL COMMENT 'Securities Code related to allotment record',
  `auction_date` date DEFAULT NULL COMMENT 'Auction happended date of the Securities',
  `issuance_date` int NOT NULL COMMENT 'To display the date on which the securities are to be issued to participants',
  `allotment_price` decimal(8,5) NOT NULL COMMENT 'Allotment price of the securies',
  `total_nominal_amount_alloted` bigint DEFAULT NULL COMMENT 'Total nominal amount alloted already for that securities',
  `total_nominal_amount_to_be_alloted` bigint DEFAULT NULL COMMENT 'Total nominal amount to be allotmented',
  `first_allotment` tinyint NOT NULL COMMENT 'To display whether the current allotment of securities is the first allotment, i.e. allotment of a new issue and not a re-opening action',
  `total_settlement_amount` decimal(18,2) NOT NULL COMMENT 'To display the total settlement amount of the securities to be issued',
  `processed` tinyint DEFAULT NULL COMMENT 'This indicator is used to check whether the allotment transaction is created or not',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `planned_total_nominal_amount_allotted` bigint DEFAULT '0' COMMENT 'Running total of allotment member nominal amount',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='allotment_na_allotment_This table is used to save the allotment';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {floating_rate_temp}
-- NOTE:
DROP table IF EXISTS floating_rate_temp;
CREATE TABLE `floating_rate_temp` (
  `id` varchar(36) NOT NULL,
  `reference_rate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Reference Rate',
  `publication_date` int NOT NULL COMMENT 'Publication Date',
  `value_date` int NOT NULL COMMENT 'Value Date',
  `rate` decimal(13,10) DEFAULT NULL COMMENT 'Rate',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
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
  `id` varchar(36) NOT NULL COMMENT 'Internal UUID, Primary key, Member''s unique identification',
  `member_code` varchar(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT 'member name',
  `short_name` varchar(30) NOT NULL COMMENT 'member’s short name',
  `member_type` varchar(3) NOT NULL COMMENT 'play the member type, shows the type belonging to the member. FOREIGN KEY - Id from member_type',
  `member_status` varchar(3) NOT NULL COMMENT 'member’s status. FOREIGN KEY - Id from member_status',
  `activation_date` date NOT NULL COMMENT 'To allow the user to set the activation date of the new member creation',
  `swift_bic` varchar(8) NOT NULL COMMENT '"member’s SWIFT\nBIC. SWIFT BIC used by a Participant at its alternate site."',
  `location` varchar(3) NOT NULL COMMENT '3-digit location code. Any transaction generated by the system will send to the location set in this field',
  `fi_code` varchar(4) NOT NULL COMMENT 'unique bank code',
  `trader_status` varchar(20) NOT NULL,
  `sector_id` varchar(30) NOT NULL COMMENT 'sector Id',
  `auto_debit_indicator` tinyint NOT NULL COMMENT 'To allow the user to select the auto debit indicator',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'To allow the user to select the Faciliy Eligibility ID. Id from facility_eligibility table as FOREIGN KEY',
  `uen` varchar(10) DEFAULT NULL COMMENT 'UEN number',
  `lei` varchar(20) DEFAULT NULL COMMENT 'LEI number',
  `statement_delivery_bic` varchar(11) DEFAULT NULL COMMENT 'statement delivery BIC',
  `end_of_day_statement` varchar(36) NOT NULL COMMENT '"• SWIFT securities statement – Statement (semt.002, semt.017, semt.018) will be sent out to the Member\n• OSP securities statement – Statement will not be sent out to the member. Member will retrieve the statement in OSP\n• No statement – Statement will not be sent out. (default value for auto creation by system)\n"',
  `exempted_from_billing` tinyint NOT NULL COMMENT 'indication whether the member is exempted from the billing',
  `exempted_from_system_limit` tinyint NOT NULL COMMENT 'indication whether the member is exempted from system limit',
  `zero_holdings_statement` varchar(20) NOT NULL COMMENT '"option when the SSS statements will be generated\n• Daily: Statement will be generated for all working days even when there are no holdings in the custody accounts.\n• Monthly: Statement will be generated on the last working day of every month even when there are no holdings in the custody accounts. For the rest of the working days of each month, the statements of accounts would only be generated when there are holdings in the custody accounts. \n• No: Daily statement will not be generated when there are no holdings in the custody accounts.\nNote: MT535 will be generated for each custody account type."',
  `rtgs_account` varchar(34) NOT NULL COMMENT 'To allow the user to input RTGS Account For Settlement.',
  `tax_profile_id` varchar(36) DEFAULT NULL COMMENT 'Its tax profile for billing',
  `billing_profile_id` varchar(36) DEFAULT NULL COMMENT 'Its fee profile for billing',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `fall_back_account` varchar(36) DEFAULT NULL COMMENT 'To allow the user to select a fall back account of the member.',
  `issuing_paying_agent` varchar(36) DEFAULT NULL COMMENT 'indicate whether member is a primary dealer.',
  `primary_dealer` varchar(36) DEFAULT NULL,
  `fi_group` char(1) NOT NULL COMMENT 'To allow the user to input the one character financial institution group',
  `account_operator` varchar(36) DEFAULT NULL,
  `verified_by` varchar(36) DEFAULT NULL,
  `verified_date` timestamp NULL DEFAULT NULL,
  `rtgs_member_code` varchar(11) NOT NULL COMMENT 'To allow the user to input RTGS Member Code.',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_member_This table is used to save the members of sss';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_code_temp}
-- NOTE:
DROP table IF EXISTS securities_code_temp;
CREATE TABLE `securities_code_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(12) NOT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(8) NOT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(45) NOT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(10) NOT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(9) NOT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(10) NOT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `currency_code` varchar(3) NOT NULL COMMENT 'Currency code of the securities',
  `denomination` bigint NOT NULL COMMENT 'denomination value',
  `issuer_id` varchar(36) NOT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(36) NOT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(35) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` tinyint NOT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` date DEFAULT NULL COMMENT 'First Auctiondate',
  `first_issuance_date` int NOT NULL COMMENT 'First issuance date of the security',
  `first_issuance_amount` bigint NOT NULL COMMENT 'First issuance amount of the security',
  `redemption_date` int DEFAULT NULL COMMENT 'Redemption date of the security',
  `optional_redemption_date` int DEFAULT NULL COMMENT 'Optional Redemption date of the securities',
  `redemption_price` decimal(9,5) NOT NULL COMMENT 'Redemption price of the securities',
  `optional_redemption_price` decimal(9,5) DEFAULT NULL COMMENT 'Optional redemption price',
  `central_bank_applied_amount` bigint NOT NULL COMMENT 'MAS applied amount',
  `coupon_structure` varchar(14) NOT NULL COMMENT 'Coupon Structure',
  `coupon_payment_frequency` varchar(18) NOT NULL COMMENT 'Coupon payment frequency',
  `coupon_rate` decimal(8,5) DEFAULT NULL COMMENT 'Coupon rate',
  `average_yield` decimal(9,5) DEFAULT NULL COMMENT 'average yield of the securities',
  `day_count_convention` varchar(20) NOT NULL COMMENT 'convention to be used fo the day count in calcualtions',
  `rounding_option` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Rounding Option to be used for that security code related calculations',
  `record_date_period` int NOT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(30) NOT NULL COMMENT 'Record status',
  `modified_by` varchar(36) NOT NULL COMMENT 'Last modified User ID',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last modified timestamp',
  `approved_by` varchar(36) NOT NULL COMMENT 'Last approved user ID',
  `approved_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Last approved timestamp',
  `created_date` timestamp NOT NULL COMMENT 'Created date of the record',
  `effective_date` timestamp NULL DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(140) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` int DEFAULT NULL COMMENT 'Approval flow ID',
  `tenor_period` int DEFAULT NULL COMMENT 'tenor period',
  `issue_price` decimal(8,5) DEFAULT NULL COMMENT 'The cut-off price is interfaced from external system (eApps).',
  `issue_yield` decimal(5,2) DEFAULT NULL COMMENT 'To display cut-off yield. The cut-off yield is interfaced from external system (eApps).',
  `redemption_reimburse` varchar(20) DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `tax_scheme_id` varchar(36) DEFAULT NULL COMMENT 'Tax scheme id - primary key of tax scheme table',
  `benchmark_indicator` varchar(36) NOT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `first_coupon_date` int DEFAULT NULL COMMENT 'First coupon date',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_code_statistics_temp}
-- NOTE:
DROP table IF EXISTS securities_code_statistics_temp;
CREATE TABLE `securities_code_statistics_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Securities code from securitities_code table',
  `total_redeemed_amount` bigint NOT NULL COMMENT 'total redeemed amount',
  `total_nominal_amount_issued_for_erf` bigint DEFAULT NULL COMMENT 'To display the total nominal amount issued for ERF.',
  `total_redeemed_amount_for_erf` bigint DEFAULT NULL COMMENT 'To display the total redeemed amount for ERF.',
  `outstanding_nominal_amount` bigint NOT NULL COMMENT 'Outstanding nominal amount',
  `next_coupon_date` int DEFAULT '0' COMMENT 'Next coupon date',
  `last_coupon_date` int DEFAULT '0' COMMENT 'Last Coupon Date',
  `next_index_end_date` int DEFAULT NULL COMMENT 'To use in FRN Coupon Rate Calculation',
  `amount_allotted_to_central_bank` bigint DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to MAS',
  `amount_allotted_to_others` bigint DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to other participants',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-code-statistics_Securities code statistics details will be saved';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_position_stats_temp}
-- NOTE:
DROP table IF EXISTS securities_position_stats_temp;
CREATE TABLE `securities_position_stats_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'COMPOSITE PRIMARY KEY - Securities code',
  `value_date` int NOT NULL COMMENT 'COMPOSITE primary key',
  `opening_nominal_amount` decimal(23,5) NOT NULL COMMENT 'The opening nominal amount will be the previous working day''s closing nominal amount.',
  `closing_nominal_amount` decimal(23,5) DEFAULT NULL COMMENT 'The current nominal amount is the net of the opening nominal amount and the current day net changes',
  `settled_count` smallint NOT NULL DEFAULT '0' COMMENT 'number of transactions settled today',
  `settled_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for settled transactions',
  `receipt_count` smallint NOT NULL DEFAULT '0' COMMENT 'number of received securities today',
  `receipt_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures of received securities today',
  `queued_count` smallint NOT NULL DEFAULT '0' COMMENT 'queued transactions count',
  `queued_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for queued transactions',
  `rejected_count` smallint NOT NULL DEFAULT '0' COMMENT 'rejected transactions count',
  `rejected_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for rejected transactions',
  `cancelled_count` smallint NOT NULL DEFAULT '0' COMMENT 'cancelled transactions count',
  `cancelled_amount` decimal(23,5) DEFAULT NULL COMMENT 'total figures for cancelled transactions',
  `account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'COMPOSITE PRIMARY KEY - account id of the sss account',
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-position-stats_This table is used to maintain the statistics based on the securities transaction';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_price_temp}
-- NOTE:
DROP table IF EXISTS securities_price_temp;
CREATE TABLE `securities_price_temp` (
  `id` varchar(36) NOT NULL,
  `securities_code` varchar(12) NOT NULL COMMENT 'COMPOSITE PRIMARY KEY - To allow the user to select from a list of available Securities code for enquiry.',
  `pricing_date` int NOT NULL COMMENT 'COPOSITE KEY - Pricing Date of Securities Price',
  `description` varchar(35) NOT NULL COMMENT 'Securities description',
  `price` decimal(8,5) NOT NULL COMMENT 'Price of the Securities.',
  `issue_code` varchar(20) NOT NULL COMMENT 'Issue Code of the Securities',
  `pricing_type` varchar(20) NOT NULL COMMENT 'COMPOSITE KEY - Pricing Type of Securities Price',
  `action` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Action of last request',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='application-param_screen_securities-price_The daily securities price will be saved in this table';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {transaction_temp}
-- NOTE:
DROP table IF EXISTS transaction_temp;
CREATE TABLE `transaction_temp` (
  `id` varchar(36) NOT NULL,
  `sss_reference` varchar(36) NOT NULL,
  `message_log_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'message ID of the message.',
  `securities_code` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'unique Securities Code of the securities in the transaction.',
  `transaction_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'type of transaction.',
  `status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'current transaction status.',
  `settlement_date` int NOT NULL COMMENT 'value date of the transaction.',
  `trade_date` int NOT NULL COMMENT 'trade date of the transaction.',
  `currency_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'currency code of the transaction.',
  `settlement_amount` decimal(23,5) DEFAULT NULL COMMENT 'settlement amount of the securities in the transaction.',
  `nominal_amount` decimal(23,5) NOT NULL COMMENT 'nominal amount of the securities in the transaction.',
  `deal_price` decimal(23,5) DEFAULT '0.00000' COMMENT 'Deal Price of the transaction.',
  `deliverer_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'deliverer member code',
  `receiver_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'receiver member code',
  `repo_closing_date` int DEFAULT '0' COMMENT 'closing date for RPO transactions',
  `repo_closing_settlement_amount` decimal(23,5) DEFAULT NULL COMMENT 'settlement amount during repo closing',
  `reason_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'return code of the status (FS - ''return code'')',
  `transaction_reference` varchar(36) DEFAULT NULL,
  `processed_date` int DEFAULT '0' COMMENT 'date that the transaction is being processed',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sender member swift BIC code',
  `payer_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'payer member swift BIC code',
  `payee_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'payee member swift BIC code',
  `receiver_member_swift_code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'receiver member swift BIC code',
  `sender_member_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sender_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sender type',
  `message_type` varchar(26) DEFAULT NULL,
  `receiver_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `deliverer_rtgs_account` varchar(34) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `receiver_rtgs_member_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS receiver.',
  `deliverer_rtgs_member_code` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member code of the RTGS deliverer.',
  `hold_indicator` tinyint NOT NULL COMMENT 'Hold indicator to hold the settlement in case of ERF closing leg transactions',
  `payment_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Payment type of the transaction',
  `grid_lock_indicator` tinyint DEFAULT NULL,
  `rollover_count` int DEFAULT '0' COMMENT 'Number of times a transaction has been rolled over',
  `pending_cancellation_indicator` tinyint DEFAULT NULL COMMENT 'To indicate that there is a cancallation request',
  `deliverer_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer of the securities in the transactions.',
  `receiver_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Receiver of the securities in the transactions.',
  `receiver_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Receiver’s securities holding  in the transactions.',
  `deliverer_account_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Account ID of the Deliverer’s securities holding  in the transactions',
  `sender_member_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Member ID of the Deliverer/Receiver message sender',
  `pdm_indicator` tinyint DEFAULT NULL,
  `erf_reference` varchar(36) DEFAULT NULL,
  `deliverer_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'deliverer account no',
  `receiver_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'receiver account no',
  `msg_received_timestamp` timestamp NULL DEFAULT NULL COMMENT '"date and time of the message created.\r\nThis is defaulted to”-“ if Channel is “System Generated”."',
  `receiver_sender_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Receiver sender member id',
  `deliverer_sender_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'deliverer sender member id',
  `deliverer_onbehalf` tinyint NOT NULL DEFAULT '0' COMMENT 'indicator to know whether it is deliverer onbehalf of or not',
  `receiver_onbehalf` tinyint NOT NULL DEFAULT '0' COMMENT 'indicator to know whether it is receiver onbehalf of or not',
  `process_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Indicator to know the current process status of transaction',
  `deliverer_beneficiary_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Deliverer beneficiary account',
  `underlying_id` varchar(42) DEFAULT NULL,
  `balance_type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `accured_interest_amount` decimal(23,5) DEFAULT '0.00000',
  `deliverer_channel` varchar(36) DEFAULT NULL COMMENT 'Channel of the deliverer',
  `receiver_channel` varchar(36) DEFAULT NULL COMMENT 'Receiver Channel',
  `repo_rate` decimal(23,10) DEFAULT '0.0000000000' COMMENT 'Repo Rate of the transaction.',
  `haircut_rate` decimal(23,5) DEFAULT '0.00000' COMMENT 'Haircut Rate of the transaction.',
  `original_account_no` varchar(35) DEFAULT NULL COMMENT 'Original Account Number',
  `remarks` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_transaction_This table is used to save the transactions which is processed in sss';
-- END