use sss_nets_mig;

-- 1. ROLLBACK SCHEMA PATCH FOR {account_source}
-- NOTE:
DROP table IF EXISTS account_source;
CREATE TABLE `account_source` (
  `id` varchar(36) DEFAULT NULL COMMENT 'Internal UUID',
  `member_id` varchar(50) DEFAULT NULL COMMENT 'Internal Member ID (UUID)',
  `account_id` varchar(50) DEFAULT NULL COMMENT 'Account ID - Unique to member',
  `description` varchar(50) DEFAULT NULL COMMENT 'Account description',
  `custody_account_type_id` varchar(50) DEFAULT NULL COMMENT 'Internal Custody Account Type ID (UUID)',
  `corporate_investor` varchar(50) DEFAULT NULL COMMENT 'LEI/UEN of the company',
  `account_status` varchar(50) DEFAULT NULL COMMENT 'Account status - Pending Activation, Active, Suspended, Closed\nDefaults to ''Pending Activation'' for new account\n',
  `tax_profile_id` varchar(50) DEFAULT NULL COMMENT 'Tax Profile Id (UUID)',
  `statement_delivery_bic` varchar(50) DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_account_This table used to save the SSS accounts';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {allotment_source}
-- NOTE:
DROP table IF EXISTS allotment_source;
CREATE TABLE `allotment_source` (
  `id` varchar(36) DEFAULT NULL COMMENT 'Primary key (UUID)',
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'Securities Code related to allotment record',
  `auction_date` varchar(50) DEFAULT NULL COMMENT 'Auction happended date of the Securities',
  `issuance_date` varchar(50) DEFAULT NULL COMMENT 'To display the date on which the securities are to be issued to participants',
  `allotment_price` varchar(50) DEFAULT NULL COMMENT 'Allotment price of the securies',
  `total_nominal_amount_alloted` varchar(50) DEFAULT NULL COMMENT 'Total nominal amount alloted already for that securities',
  `total_nominal_amount_to_be_alloted` varchar(50) DEFAULT NULL COMMENT 'Total nominal amount to be allotmented',
  `first_allotment` varchar(50) DEFAULT NULL COMMENT 'To display whether the current allotment of securities is the first allotment, i.e. allotment of a new issue and not a re-opening action',
  `total_settlement_amount` varchar(50) DEFAULT NULL COMMENT 'To display the total settlement amount of the securities to be issued',
  `processed` varchar(50) DEFAULT NULL COMMENT 'This indicator is used to check whether the allotment transaction is created or not',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `planned_total_nominal_amount_allotted` varchar(50) DEFAULT NULL COMMENT 'Running total of allotment member nominal amount',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='allotment_na_allotment_This table is used to save the allotment';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {floating_rate_source}
-- NOTE:
DROP table IF EXISTS floating_rate_source;
CREATE TABLE `floating_rate_source` (
  `id` varchar(36) DEFAULT NULL,
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
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ca_screen_floating-rate_This table is used to save the calculated cash rate.';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {member_source}
-- NOTE:
DROP table IF EXISTS member_source;
CREATE TABLE `member_source` (
  `id` varchar(36) DEFAULT NULL COMMENT 'Internal UUID, Primary key, Member''s unique identification',
  `member_code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `short_name` varchar(50) DEFAULT NULL COMMENT 'member’s short name',
  `member_type` varchar(50) DEFAULT NULL COMMENT 'play the member type, shows the type belonging to the member. FOREIGN KEY - Id from member_type',
  `member_status` varchar(50) DEFAULT NULL COMMENT 'member’s status. FOREIGN KEY - Id from member_status',
  `activation_date` varchar(50) DEFAULT NULL COMMENT 'To allow the user to set the activation date of the new member creation',
  `swift_bic` varchar(50) DEFAULT NULL COMMENT '"member’s SWIFT\nBIC. SWIFT BIC used by a Participant at its alternate site."',
  `location` varchar(50) DEFAULT NULL COMMENT '3-digit location code. Any transaction generated by the system will send to the location set in this field',
  `fi_code` varchar(50) DEFAULT NULL COMMENT 'unique bank code',
  `trader_status` varchar(50) DEFAULT NULL,
  `sector_id` varchar(50) DEFAULT NULL COMMENT 'sector Id',
  `auto_debit_indicator` varchar(50) DEFAULT NULL COMMENT 'To allow the user to select the auto debit indicator',
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
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `fall_back_account` varchar(50) DEFAULT NULL COMMENT 'To allow the user to select a fall back account of the member.',
  `issuing_paying_agent` varchar(50) DEFAULT NULL COMMENT 'indicate whether member is a primary dealer.',
  `primary_dealer` varchar(50) DEFAULT NULL,
  `fi_group` varchar(50) DEFAULT NULL COMMENT 'To allow the user to input the one character financial institution group',
  `account_operator` varchar(50) DEFAULT NULL,
  `verified_by` varchar(50) DEFAULT NULL,
  `verified_date` varchar(50) DEFAULT NULL,
  `rtgs_member_code` varchar(50) DEFAULT NULL COMMENT 'To allow the user to input RTGS Member Code.',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='member_screen_member_This table is used to save the members of sss';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_code_source}
-- NOTE:
DROP table IF EXISTS securities_code_source;
CREATE TABLE `securities_code_source` (
  `id` varchar(50) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'PRIMARY KEY - securities code',
  `issue_code` varchar(50) DEFAULT NULL COMMENT 'Issue Code of the Securities',
  `description` varchar(50) DEFAULT NULL COMMENT 'description of the securities code',
  `securities_type_id` varchar(50) DEFAULT NULL COMMENT 'securities type from the securities_type table',
  `securities_status` varchar(50) DEFAULT NULL COMMENT 'Status of the securities',
  `securities_tenor_period_unit` varchar(50) DEFAULT NULL COMMENT 'tenor period - DAYS, MONTHS, YEARS',
  `currency_code` varchar(50) DEFAULT NULL COMMENT 'Currency code of the securities',
  `denomination` varchar(50) DEFAULT NULL COMMENT 'denomination value',
  `issuer_id` varchar(50) DEFAULT NULL COMMENT 'Issuer of the securities',
  `ipa` varchar(50) DEFAULT NULL COMMENT 'Member code with IPA=Yes',
  `facility_eligibility_id` varchar(50) DEFAULT NULL COMMENT 'Facility Eligibility ID tied to security code.',
  `haircut_id` varchar(50) DEFAULT NULL COMMENT 'Hair cut id from Hair cut table',
  `tradable` varchar(50) DEFAULT NULL COMMENT 'Indicator to know whether security itradable or not',
  `first_auction_date` varchar(50) DEFAULT NULL COMMENT 'First Auctiondate',
  `first_issuance_date` varchar(50) DEFAULT NULL COMMENT 'First issuance date of the security',
  `first_issuance_amount` varchar(50) DEFAULT NULL COMMENT 'First issuance amount of the security',
  `redemption_date` varchar(50) DEFAULT NULL COMMENT 'Redemption date of the security',
  `optional_redemption_date` varchar(50) DEFAULT NULL COMMENT 'Optional Redemption date of the securities',
  `redemption_price` varchar(50) DEFAULT NULL COMMENT 'Redemption price of the securities',
  `optional_redemption_price` varchar(50) DEFAULT NULL COMMENT 'Optional redemption price',
  `central_bank_applied_amount` varchar(50) DEFAULT NULL COMMENT 'MAS applied amount',
  `coupon_structure` varchar(50) DEFAULT NULL COMMENT 'Coupon Structure',
  `coupon_payment_frequency` varchar(50) DEFAULT NULL COMMENT 'Coupon payment frequency',
  `coupon_rate` varchar(50) DEFAULT NULL COMMENT 'Coupon rate',
  `average_yield` varchar(50) DEFAULT NULL COMMENT 'average yield of the securities',
  `day_count_convention` varchar(50) DEFAULT NULL COMMENT 'convention to be used fo the day count in calcualtions',
  `rounding_option` varchar(50) DEFAULT NULL COMMENT 'Rounding Option to be used for that security code related calculations',
  `record_date_period` varchar(50) DEFAULT NULL COMMENT 'input the duration (in business days) prior to coupon payment, to be specified by the user in order to derive the Record Date',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `tenor_period` varchar(50) DEFAULT NULL COMMENT 'tenor period',
  `issue_price` varchar(50) DEFAULT NULL COMMENT 'The cut-off price is interfaced from external system (eApps).',
  `issue_yield` varchar(50) DEFAULT NULL COMMENT 'To display cut-off yield. The cut-off yield is interfaced from external system (eApps).',
  `redemption_reimburse` varchar(50) DEFAULT NULL COMMENT 'redemption or reimbursement type of the securities. Possible values are fixed maturity and perpertual',
  `tax_scheme_id` varchar(50) DEFAULT NULL COMMENT 'Tax scheme id - primary key of tax scheme table',
  `benchmark_indicator` varchar(50) DEFAULT NULL COMMENT 'To allow the user to select the benchmark indicator.  It indicates whether the securities code is eligible to be benchmark',
  `first_coupon_date` varchar(50) DEFAULT NULL COMMENT 'First coupon date',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='biz-param_screen_securities-code_This is the configuration table of securities';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_code_statistics_source}
-- NOTE:
DROP table IF EXISTS securities_code_statistics_source;
CREATE TABLE `securities_code_statistics_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'Securities code from securitities_code table',
  `total_redeemed_amount` varchar(50) DEFAULT NULL COMMENT 'total redeemed amount',
  `total_nominal_amount_issued_for_erf` varchar(50) DEFAULT NULL COMMENT 'To display the total nominal amount issued for ERF.',
  `total_redeemed_amount_for_erf` varchar(50) DEFAULT NULL COMMENT 'To display the total redeemed amount for ERF.',
  `outstanding_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'Outstanding nominal amount',
  `next_coupon_date` varchar(50) DEFAULT NULL COMMENT 'Next coupon date',
  `last_coupon_date` varchar(50) DEFAULT NULL COMMENT 'Last Coupon Date',
  `next_index_end_date` varchar(50) DEFAULT NULL COMMENT 'To use in FRN Coupon Rate Calculation',
  `amount_allotted_to_central_bank` varchar(50) DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to MAS',
  `amount_allotted_to_others` varchar(50) DEFAULT NULL COMMENT 'To display the nominal amount of the securities allotted to other participants',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-code-statistics_Securities code statistics details will be saved';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_position_stats_source}
-- NOTE:
DROP table IF EXISTS securities_position_stats_source;
CREATE TABLE `securities_position_stats_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - Securities code',
  `value_date` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE primary key',
  `opening_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'The opening nominal amount will be the previous working day''s closing nominal amount.',
  `closing_nominal_amount` varchar(50) DEFAULT NULL COMMENT 'The current nominal amount is the net of the opening nominal amount and the current day net changes',
  `settled_count` varchar(50) DEFAULT NULL COMMENT 'number of transactions settled today',
  `settled_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for settled transactions',
  `receipt_count` varchar(50) DEFAULT NULL COMMENT 'number of received securities today',
  `receipt_amount` varchar(50) DEFAULT NULL COMMENT 'total figures of received securities today',
  `queued_count` varchar(50) DEFAULT NULL COMMENT 'queued transactions count',
  `queued_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for queued transactions',
  `rejected_count` varchar(50) DEFAULT NULL COMMENT 'rejected transactions count',
  `rejected_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for rejected transactions',
  `cancelled_count` varchar(50) DEFAULT NULL COMMENT 'cancelled transactions count',
  `cancelled_amount` varchar(50) DEFAULT NULL COMMENT 'total figures for cancelled transactions',
  `account_id` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - account id of the sss account',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_securities-position-stats_This table is used to maintain the statistics based on the securities transaction';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {securities_price_source}
-- NOTE:
DROP table IF EXISTS securities_price_source;
CREATE TABLE `securities_price_source` (
  `id` varchar(36) DEFAULT NULL,
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE PRIMARY KEY - To allow the user to select from a list of available Securities code for enquiry.',
  `pricing_date` varchar(50) DEFAULT NULL COMMENT 'COPOSITE KEY - Pricing Date of Securities Price',
  `description` varchar(50) DEFAULT NULL COMMENT 'Securities description',
  `price` varchar(50) DEFAULT NULL COMMENT 'Price of the Securities.',
  `issue_code` varchar(50) DEFAULT NULL COMMENT 'Issue Code of the Securities',
  `pricing_type` varchar(50) DEFAULT NULL COMMENT 'COMPOSITE KEY - Pricing Type of Securities Price',
  `action` varchar(50) DEFAULT NULL COMMENT 'Action of last request',
  `status` varchar(50) DEFAULT NULL COMMENT 'Record status',
  `modified_by` varchar(50) DEFAULT NULL COMMENT 'Last modified User ID',
  `modified_date` varchar(50) DEFAULT NULL COMMENT 'Last modified timestamp',
  `approved_by` varchar(50) DEFAULT NULL COMMENT 'Last approved user ID',
  `approved_date` varchar(50) DEFAULT NULL COMMENT 'Last approved timestamp',
  `created_date` varchar(50) DEFAULT NULL COMMENT 'Created date of the record',
  `effective_date` varchar(50) DEFAULT NULL COMMENT 'Activation date of the record',
  `approval_remark` varchar(50) DEFAULT NULL COMMENT 'Last approver remark',
  `workflow_status_id` varchar(50) DEFAULT NULL COMMENT 'Approval flow ID',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='application-param_screen_securities-price_The daily securities price will be saved in this table';
-- END

-- 1. ROLLBACK SCHEMA PATCH FOR {transaction_source}
-- NOTE:
DROP table IF EXISTS transaction_source;
CREATE TABLE `transaction_source` (
  `id` varchar(36) DEFAULT NULL,
  `sss_reference` varchar(50) DEFAULT NULL,
  `message_log_id` varchar(50) DEFAULT NULL COMMENT 'message ID of the message.',
  `securities_code` varchar(50) DEFAULT NULL COMMENT 'unique Securities Code of the securities in the transaction.',
  `transaction_type` varchar(50) DEFAULT NULL COMMENT 'type of transaction.',
  `status` varchar(50) DEFAULT NULL COMMENT 'current transaction status.',
  `settlement_date` varchar(50) DEFAULT NULL COMMENT 'value date of the transaction.',
  `trade_date` varchar(50) DEFAULT NULL COMMENT 'trade date of the transaction.',
  `currency_code` varchar(50) DEFAULT NULL COMMENT 'currency code of the transaction.',
  `settlement_amount` varchar(50) DEFAULT NULL COMMENT 'settlement amount of the securities in the transaction.',
  `nominal_amount` varchar(50) DEFAULT NULL COMMENT 'nominal amount of the securities in the transaction.',
  `deal_price` varchar(50) DEFAULT NULL COMMENT 'Deal Price of the transaction.',
  `deliverer_member_code` varchar(50) DEFAULT NULL COMMENT 'deliverer member code',
  `receiver_member_code` varchar(50) DEFAULT NULL COMMENT 'receiver member code',
  `repo_closing_date` varchar(50) DEFAULT NULL COMMENT 'closing date for RPO transactions',
  `repo_closing_settlement_amount` varchar(50) DEFAULT NULL COMMENT 'settlement amount during repo closing',
  `reason_code` varchar(50) DEFAULT NULL COMMENT 'return code of the status (FS - ''return code'')',
  `transaction_reference` varchar(50) DEFAULT NULL,
  `processed_date` varchar(50) DEFAULT NULL COMMENT 'date that the transaction is being processed',
  `created_date` varchar(50) DEFAULT NULL,
  `modified_date` varchar(50) DEFAULT NULL,
  `sender_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'sender member swift BIC code',
  `payer_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'payer member swift BIC code',
  `payee_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'payee member swift BIC code',
  `receiver_member_swift_code` varchar(50) DEFAULT NULL COMMENT 'receiver member swift BIC code',
  `sender_member_code` varchar(50) DEFAULT NULL,
  `sender_type` varchar(50) DEFAULT NULL COMMENT 'sender type',
  `message_type` varchar(50) DEFAULT NULL,
  `receiver_rtgs_account` varchar(50) DEFAULT NULL,
  `deliverer_rtgs_account` varchar(50) DEFAULT NULL,
  `receiver_rtgs_member_code` varchar(50) DEFAULT NULL COMMENT 'Member code of the RTGS receiver.',
  `deliverer_rtgs_member_code` varchar(50) DEFAULT NULL COMMENT 'Member code of the RTGS deliverer.',
  `hold_indicator` varchar(50) DEFAULT NULL COMMENT 'Hold indicator to hold the settlement in case of ERF closing leg transactions',
  `payment_type` varchar(50) DEFAULT NULL COMMENT 'Payment type of the transaction',
  `grid_lock_indicator` varchar(50) DEFAULT NULL,
  `rollover_count` varchar(50) DEFAULT NULL COMMENT 'Number of times a transaction has been rolled over',
  `pending_cancellation_indicator` varchar(50) DEFAULT NULL COMMENT 'To indicate that there is a cancallation request',
  `deliverer_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Deliverer of the securities in the transactions.',
  `receiver_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Receiver of the securities in the transactions.',
  `receiver_account_id` varchar(50) DEFAULT NULL COMMENT 'Account ID of the Receiver’s securities holding  in the transactions.',
  `deliverer_account_id` varchar(50) DEFAULT NULL COMMENT 'Account ID of the Deliverer’s securities holding  in the transactions',
  `sender_member_id` varchar(50) DEFAULT NULL COMMENT 'Member ID of the Deliverer/Receiver message sender',
  `pdm_indicator` varchar(50) DEFAULT NULL,
  `erf_reference` varchar(50) DEFAULT NULL,
  `deliverer_account_no` varchar(50) DEFAULT NULL COMMENT 'deliverer account no',
  `receiver_account_no` varchar(50) DEFAULT NULL COMMENT 'receiver account no',
  `msg_received_timestamp` varchar(50) DEFAULT NULL COMMENT '"date and time of the message created.\r\nThis is defaulted to”-“ if Channel is “System Generated”."',
  `receiver_sender_id` varchar(50) DEFAULT NULL COMMENT 'Receiver sender member id',
  `deliverer_sender_id` varchar(50) DEFAULT NULL COMMENT 'deliverer sender member id',
  `deliverer_onbehalf` varchar(50) DEFAULT NULL COMMENT 'indicator to know whether it is deliverer onbehalf of or not',
  `receiver_onbehalf` varchar(50) DEFAULT NULL COMMENT 'indicator to know whether it is receiver onbehalf of or not',
  `process_flag` varchar(50) DEFAULT NULL COMMENT 'Indicator to know the current process status of transaction',
  `deliverer_beneficiary_account` varchar(50) DEFAULT NULL COMMENT 'Deliverer beneficiary account',
  `underlying_id` varchar(50) DEFAULT NULL,
  `balance_type` varchar(50) DEFAULT NULL,
  `accured_interest_amount` varchar(50) DEFAULT NULL,
  `deliverer_channel` varchar(50) DEFAULT NULL,
  `receiver_channel` varchar(50) DEFAULT NULL COMMENT 'Receiver Channel',
  `repo_rate` varchar(50) DEFAULT NULL COMMENT 'Repo Rate of the transaction.',
  `haircut_rate` varchar(50) DEFAULT NULL COMMENT 'Haircut Rate of the transaction.',
  `original_account_no` varchar(50) DEFAULT NULL COMMENT 'Original Account Number',
  `seq` int NOT NULL AUTO_INCREMENT,
  `remarks` varchar(400) DEFAULT NULL,
  `migrated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='txn-flow_na_transaction_This table is used to save the transactions which is processed in sss';
-- END