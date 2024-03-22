use rtgs_nets_mig_new;

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
  `remarks` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- END