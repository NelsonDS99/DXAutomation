package com.cepheid.dx.automation.core;

public class Locators extends InputDialog
{

  protected String locator (String args)
  {
    return String.format("%s%s", System, args);
  }

  // Application
  protected final String System                       = "GeneXpert® Dx System.";

  // Header
  protected final String USER_TAB                     = "/JMenu[@caption='User']";
  protected final String DATA_MNG_TAB                 = "/JMenu[@caption='Data Management']";
  protected final String REPORTS_TAB                  = "/JMenu[@caption='Reports']";
  protected final String SETUP_TAB                    = "/JMenu[@caption='Setup']";
  protected final String MAINT_TAB                    = "/JMenu[@caption='Maintenance']";
  protected final String ABOUT_TAB                    = "/JMenu[@caption='About']";
  protected final String USER_LABEL                   = "/JLabel[@caption='User <None>']";

  // Banner
  protected final String BANNER                       = "//JPanel[@className='q1']/JPanel";
  protected final String CREATE_TEST_BTN              = "//JButton[@caption='Create Test']";
  protected final String CHECK_STATUS_BTN             = "//JToggleButton[@caption='Check Status']";
  protected final String STOP_TEST_BTN                = "//JButton[@caption='Stop Test']";
  protected final String VIEW_RESULTS_BTN             = "//JToggleButton[@caption='View Results']";
  protected final String DEFINE_ASSAYS_BTN            = "//JToggleButton[@caption='Define Assays']";
  protected final String DEFINE_GRAPHS_BTN            = "//JToggleButton[@caption='Define Graphs']";
  protected final String MAINTENANCE_BTN              = "//JToggleButton[@caption='Maintenance']";

  // Module Window
  protected final String MODULE_BNR                   = "//JLabel[@caption='Modules']";
  protected final String MOD_TABLE_BNR                = "//JPanel[@className='z3']//JTableHeader";
  protected final String MOD_WINDOW                   = "//JPanel[@className='z3']//JViewport";

  // Tests Since Launch Window
  protected final String TSL_BNR                      = "//JLabel[@caption='Tests Since Launch']";
  protected final String TSL_TABLE_BNR                = "//JPanel[@className='_g']//JTableHeader";
  protected final String TSL_WINDOW                   = "//JPanel[@className='_g']//JTableHeader";

  // Miscellaneous General Page
  protected final String VERTICAL_DIVIDER             = "//JSplitPane/JSplitPane/SplitPaneDivider[@className='MetalSplitPaneDivider']";
  protected final String VERTICAL_LEFT_ARROW_BTN      = "//JSplitPane/JSplitPane/SplitPaneDivider[@className='MetalSplitPaneDivider']/JButton[@className='MetalSplitPaneDivider$1']";
  protected final String VERTICAL_RIGHT_ARROW_BTN     = "//JSplitPane/JSplitPane/SplitPaneDivider[@className='MetalSplitPaneDivider']/JButton[@className='MetalSplitPaneDivider$2']";
  protected final String HORIZON_DIVIDER              = "//SplitPaneDivider[@className='MetalSplitPaneDivider'][2]";
  protected final String HORIZON_UP_ARROW_BTN         = "//SplitPaneDivider[@className='MetalSplitPaneDivider'][2]/JButton[@className='MetalSplitPaneDivider$1']";
  protected final String HORIZON_DOWN_ARROW_BTN       = "//SplitPaneDivider[@className='MetalSplitPaneDivider'][2]/JButton[@className='MetalSplitPaneDivider$2']";

  // Messages Window
  protected final String MSGS_WIN                     = "//JScrollPane[@caption='Messages:']";
  protected final String MSGS_TXT_BOX                 = "//JTextArea[@priorlabel='Messages:']";
  protected final String MSGS_SCROLL_BAR              = "//JVerticalScrollBar";

  // User Menu
  protected final String LOGIN_BTN                    = "//JMenuItem[@caption='Login']";
  protected final String CHANGE_PSWRD_BTN             = "//JMenuItem[@caption='Change Password']";
  protected final String LOGOUT_BTN                   = "//JMenuItem[@caption='Logout']";
  protected final String EXIT_BTN                     = "//JMenuItem[@caption='Exit']";

  // Data Management Menu
  protected final String ARCHIVE_TEST_BTN             = "//JMenuItem[@caption='Archive Test']";
  protected final String RETRIEVE_TEST_BTN            = "//JMenuItem[@caption='Retrieve Test']";

  // About Menu
  protected final String ABOUT_SYSTEM_BTN             = "//JMenuItem[@caption='About GeneXpert® Dx System']";

  // Reports Menu
  protected final String SPECIMEN_REPORT_BTN          = "//JMenuItem[@caption='Specimen Report']";
  protected final String PATIENT_REPORT_BTN           = "//JMenuItem[@caption='Patient Report']";
  protected final String PATIENT_TREND_REPORT_BTN     = "//JMenuItem[@caption='Patient Trend Report']";
  protected final String CONTROL_TREND_REPORT_BTN     = "//JMenuItem[@caption='Control Trend Report']";
  protected final String SYSTEM_LOG_BTN               = "//JMenuItem[@caption='System Log']";
  protected final String ASSAY_STATISTICS_REPORT_BTN  = "//JMenuItem[@caption='Assay Statistics Report']";
  protected final String INSTALLATION_QUALIFICATION   = "//JMenuItem[@caption='Installation Qualification']";

  // Setup Menu
  protected final String USER_ADMINISTRATION          = "//JMenuItem[@caption='User Administration']";
  protected final String USER_TYPE_CONFIGURATION      = "//JMenuItem[@caption='User Type Configuration']";
  protected final String SYSTEM_CONFIGURATION         = "//JMenuItem[@caption='System Configuration']";
  protected final String ASSIGN_INSTRUMENT_LETTER     = "//JMenuItem[@caption='Assign Instrument Letter']";

  // Maintenance Menu
  protected final String MODULE_REPORTERS             = "//JMenuItem[@caption='Module Reporters']";
  protected final String PLUNGER_ROD_MAINT            = "//JMenuItem[@caption='Plunger Rod Maintenance']";
  protected final String VALVE_MAINT                  = "//JMenuItem[@caption='Valve Maintenance']";
  protected final String PERFORM_SELF_TEST            = "//JMenuItem[@caption='Perform Self-Test']";
  protected final String OPEN_MOD_DOOR                = "//JMenuItem[@caption='Open Module Door or Update EEPROM']";
  protected final String EXCLUDE_MOD_FROM_TEST        = "//JMenuItem[@caption='Exclude Modules From Test']";

  // Login Menu

  protected final String LOGIN_WND                    = "/JDialog[@caption='Login']";
  protected final String USER_NAME_LABEL              = "/JDialog[@caption='Login']//JLabel[@caption='User Name']";
  protected final String USER_NAME_BOX                = locator(
      "/JDialog[@caption='Login']//JTextField[@priorlabel='User Name']");
  protected final String PASSWORD_LABEL               = "/JDialog[@caption='Login']//JLabel[@caption='Password']";
  protected final String PASSWORD_BOX                 = locator("/JDialog[@caption='Login']//JPasswordField[@priorlabel='Password']");
  protected final String LOGIN_OK_BTN                 = locator("/JDialog[@caption='Login']//JButton[@caption='OK']");
  protected final String LOGIN_CANCEL_BTN             = "/JDialog[@caption='Login']//JButton[@caption='Cancel']";

  // Archive Test Window
  protected final String ARCHIVE_TEST_WND             = "/JDialog[@caption='Select Test(s) To Be Archived']";
  protected final String CLOAK_ID_CHCK                = "/JDialog[@caption='Select Test(s) To Be Archived']//JCheckBox[@caption='Cloak IDs']";
  protected final String PURGE_SELECTED_TESTS_CHCK    = "/JDialog[@caption='Select Test(s) To Be Archived']//JCheckBox[@caption='Purge Selected Tests from List After Archiving (Recommended Monthly)']";
  protected final String SELECT_ALL_BTN               = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Select All']";
  protected final String DESELECT_ALL_BTN             = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Deselect All']";
  protected final String SELECTED_HGLHT_BTN           = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Select Highlighted']";
  protected final String DESELECTED_HGLHT_BTN         = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Deselect Highlighted']";
  protected final String SELECTED_NEW_BTN             = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Select New Archive']";
  protected final String ARCH_OK_BTN                  = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='OK']";
  protected final String ARCH_CANCEL_BTN              = "/JDialog[@caption='Select Test(s) To Be Archived']//JButton[@caption='Cancel']";

  // Retrieve Test Window
  protected final String RETRIEVE_TEST_WND            = "/JDialog[@caption='Open']";
  protected final String LOOK_IN_LABEL                = "/JDialog[@caption='Open']//JLabel[@caption='Look In:']";
  protected final String FILE_DROPDOWN_BTN            = "/JDialog[@caption='Open']//JComboBox[@priorlabel='Look In:']";
  protected final String UP_LEVEL_BTN                 = "/JDialog[@caption='Open']//JButton";
  protected final String DESKTOP_BTN                  = "/JDialog[@caption='Open']//JButton[2]";
  protected final String NEW_FOLDER_BTN               = "/JDialog[@caption='Open']//JButton[3]";
  protected final String NAME_VIEW_BTN                = "/JDialog[@caption='Open']//JToggleButton";
  protected final String DETAILS_VIEW_BTN             = "/JDialog[@caption='Open']//JToggleButton[2]";
  protected final String FILE_NAME_LABEL              = "/JDialog[@caption='Open']//JLabel[@caption='File Name:']";
  protected final String FILE_NAME_BOX                = "/JDialog[@caption='Open']//JTextField[@priorlabel='File Name:']";
  protected final String FILE_TYPE_LABEL              = "/JDialog[@caption='Open']//JLabel[@caption='Files of Type:']";
  protected final String FILE_TYPE_BOX                = "/JDialog[@caption='Open']//JTextField[@priorlabel='File Name:']";
  protected final String OPEN_BTN                     = "/JDialog[@caption='Open']//JButton[@caption='Open']";
  protected final String CANCEL_BTN                   = "/JDialog[@caption='Open']//JButton[@caption='Cancel']";

  // Specimen Report Window

  protected final String SPECIMEN_REPORT_WND          = "/JDialog[@caption='Specimen Report']";
  protected final String ALL_BTN                      = "/JDialog[@caption='Specimen Report']//JRadioButton[@caption='All']";
  protected final String SELECT_BTN                   = "/JDialog[@caption='Specimen Report']//JRadioButton[@caption='Select']";
  protected final String FROM_LABEL                   = "/JDialog[@caption='Specimen Report']//JLabel[@caption='From']";
  protected final String FROM_BOX                     = "/JDialog[@caption='Specimen Report']//JTextField[@priorlabel='From']";
  protected final String SP_TO_LABEL                  = "/JDialog[@caption='Specimen Report']//JLabel[@caption='To']";
  protected final String SP_TO_BOX                    = "/JDialog[@caption='Specimen Report']//JTextField[@priorlabel='To']";
  protected final String SP_SAMPLE_ID_LABEL           = "/JDialog[@caption='Specimen Report']//JLabel[@caption='Sample ID:']";
  protected final String SP_SAMPLE_ID_BOX             = "/JDialog[@caption='Specimen Report']//JTextField[@priorlabel='Sample ID:']";
  protected final String GEN_REPORT_FILE_BTN          = "/JDialog[@caption='Specimen Report']//JButton[@caption='Generate Report File']";
  protected final String PREVIEW_BTN                  = "/JDialog[@caption='Specimen Report']//JButton[@caption='Preview PDF']";
  protected final String CLOSE_BTN                    = "/JDialog[@caption='Specimen Report']//JButton[@caption='Close']";

  // Patient Report
  protected final String PATIENT_REPORT_WND           = "/JDialog[@caption='Patient Report']";
  protected final String PAT_ALL_BTN                  = "/JDialog[@caption='Patient Report']//JRadioButton[@caption='All']";
  protected final String PAT_SELECT_BTN               = "/JDialog[@caption='Patient Report']//JRadioButton[@caption='Select']";
  protected final String PAT_FROM_LABEL               = "/JDialog[@caption='Patient Report']//JLabel[@caption='From']";
  protected final String PAT_FROM_BOX                 = "/JDialog[@caption='Patient Report']//JTextField[@priorlabel='From']";
  protected final String PAT_TO_LABEL                 = "/JDialog[@caption='Patient Report']//JLabel[@caption='To']";
  protected final String PAT_TO_BOX                   = "/JDialog[@caption='Patient Report']//JTextField[@priorlabel='To']";
  protected final String PAT_SAMPLE_ID_LABEL          = "/JDialog[@caption='Patient Report']//JLabel[@caption='Sample ID:']";
  protected final String PAT_SAMPLE_ID_BOX            = "/JDialog[@caption='Patient Report']//JTextField[@priorlabel='Sample ID:']";
  protected final String PAT_GEN_REPORT_FILE_BTN      = "/JDialog[@caption='Patient Report']//JButton[@caption='Generate Report File']";
  protected final String PAT_PREVIEW_BTN              = "/JDialog[@caption='Patient Report']//JButton[@caption='Preview PDF']";
  protected final String PAT_CLOSE_BTN                = "/JDialog[@caption='Patient Report']//JButton[@caption='Close']";

  // Patient Trend Report
  protected final String PAT_TREND_REPORT             = "/JDialog[@caption='Patient Trend Report']";
  protected final String PAT_TREND_ALL_BTN            = "/JDialog[@caption='Patient Trend Report']//JRadioButton[@caption='All']";
  protected final String PAT_TREND_SELECT_BTN         = "/JDialog[@caption='Patient Trend Report']//JRadioButton[@caption='Select']";
  protected final String PAT_TREND_FROM_LABEL         = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='From']";
  protected final String PAT_TREND_FROM_BOX           = "/JDialog[@caption='Patient Trend Report']//JTextField[@priorlabel='From']";
  protected final String PAT_TREND_TO_LABEL           = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='To']";
  protected final String PAT_TREND_TO_BOX             = "/JDialog[@caption='Patient Trend Report']//JTextField[@priorlabel='To']";
  protected final String PAT_TREND_PATIENT_ID_LABEL   = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='Patient ID']";
  protected final String PAT_TREND_PAT_ID_BOX         = "/JDialog[@caption='Patient Trend Report']//JTextField[@priorlabel='Patient ID']";
  protected final String PAT_TREND_ELLIPSIS           = "/JDialog[@caption='Patient Trend Report']//JButton[@caption='…']";
  protected final String PAT_TREND_SELECT_ASSAY_LABEL = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='Select Assay']";
  protected final String PAT_TREND_ASSAY_LABEL        = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='Assay']";
  protected final String PAT_TREND_VERSION_LABEL      = "/JDialog[@caption='Patient Trend Report']//JPanel[@className='j0']/JPanel[2]";
  protected final String PAT_TREND_ASSAY_BOX          = "/JDialog[@caption='Patient Trend Report']//JComboBox[@priorlabel='Assay']";
  protected final String PAT_TREND_SHOW_LABEL         = "/JDialog[@caption='Patient Trend Report']//JLabel[@caption='Show target reference line at']";
  protected final String SHOW_TARGET_REF_BOX          = "/JDialog[@caption='Patient Trend Report']//JTextField[@priorlabel='Select Assay']";
  protected final String Y_AXIS_MAX                   = "/JDialog[@caption='Patient Trend Report']//JCheckBox[@caption='Specify y-axis maximum value']";
  protected final String Y_AXIS_MAX_BOX               = "/JDialog[@caption='Patient Trend Report']//JTextField[@priorlabel='Select Assay'][2]";
  protected final String PLOT_QUANT_CHK               = "/JDialog[@caption='Patient Trend Report']//JCheckBox[@caption='Plot quantitative value in log format']";
  protected final String VIEW_GRAPH_BTN               = "/JDialog[@caption='Patient Trend Report']//JButton[@caption='View Graph']";
  protected final String PAT_TREND_GEN_BTN            = "/JDialog[@caption='Patient Trend Report']//JButton[@caption='Generate Report File']";
  protected final String PAT_TREND_PREVIEW_BTN        = "/JDialog[@caption='Patient Trend Report']//JButton[@caption='Preview PDF']";
  protected final String PAT_TREND_CLOSE_BTN          = "/JDialog[@caption='Patient Trend Report']//JButton[@caption='Close']";

  // Control Trend Report
  protected final String CONTROL_TREND_WND            = "/JDialog[@caption='Control Trend Report']";
  protected final String CT_ALL_BTN                   = "/JDialog[@caption='Control Trend Report']//JRadioButton[@caption='All']";
  protected final String CT_SELECT_BTN                = "/JDialog[@caption='Control Trend Report']//JRadioButton[@caption='Select']";
  protected final String CT_FROM_LABEL                = "/JDialog[@caption='Control Trend Report']//JLabel[@caption='From']";
  protected final String CT_FROM_BOX                  = "/JDialog[@caption='Control Trend Report']//JTextField[@priorlabel='From']";
  protected final String CT_TO_LABEL                  = "/JDialog[@caption='Control Trend Report']//JLabel[@caption='To']";
  protected final String CT_TO_BOX                    = "/JDialog[@caption='Control Trend Report']//JTextField[@priorlabel='To']";
  protected final String CT_ASSAY_TABLE               = "/JDialog[@caption='Control Trend Report']//JPanel[@caption='Assays']";
  protected final String CT_ASSAY_HEADER              = "/JDialog[@caption='Control Trend Report']//JTableHeader";
  protected final String CT_ASSAY_GRID                = "/JDialog[@caption='Control Trend Report']//JViewport";
  protected final String CT_REAGENT_LOT_LABEL         = "/JDialog[@caption='Control Trend Report']//JLabel[@caption='Reagent Lot Number']";
  protected final String CT_REAGENT_DROPDOWN          = "/JDialog[@caption='Control Trend Report']//JComboBox[@priorlabel='Reagent Lot Number']";
  protected final String CT_TEST_TYPES_SEC            = "/JDialog[@caption='Control Trend Report']//JPanel[@caption='Test Type(s)']";
  protected final String CT_NEG_CON_1_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Negative Control 1']";
  protected final String CT_NEG_CON_2_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Negative Control 2']";
  protected final String CT_NEG_CON_3_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Exclude tests in which any target analyte is positive']";
  protected final String CT_POS_CON_1_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Positive Control 1']";
  protected final String CT_POS_CON_2_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Positive Control 2']";
  protected final String CT_POS_CON_3_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Positive Control 3']";
  protected final String SPECIMEN_CHK                 = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Specimen']";
  protected final String SEL_ANALYTES_BTN             = "/JDialog[@caption='Control Trend Report']//JButton[@caption='Select Analytes']";
  protected final String DATA_TYPES_SEC               = "/JDialog[@caption='Control Trend Report']//JPanel[@caption='Data Type']";
  protected final String ENDPT_CHK                    = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='EndPt']";
  protected final String CYCLE_THRESH_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='EndPt']";
  protected final String EXCLUDE_TEST_CHK             = "/JDialog[@caption='Control Trend Report']//JCheckBox[@caption='Exclude tests in which any target analyte is positive']";
  protected final String CT_GEN_REPORT_BTN            = "/JDialog[@caption='Control Trend Report']//JButton[@caption='Generate Report File']";
  protected final String CT_PREVIEW_PDF_BTN           = "/JDialog[@caption='Control Trend Report']//JButton[@caption='Preview PDF']";
  protected final String CT_CLOSE_BTN                 = "/JDialog[@caption='Control Trend Report']//JButton[@caption='Close']";

  // System Log Window
  protected final String SYSTEM_LOG_WND               = "/JDialog[@caption='System Log Report']";
  protected final String SL_ALL_BTN                   = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='All']";
  protected final String SL_SELECT_BTN                = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='Select']";
  protected final String SL_FROM_LABEL                = "/JDialog[@caption='System Log Report']//JLabel[@caption='From']";
  protected final String SL_FROM_BOX                  = "/JDialog[@caption='System Log Report']//JTextField[@priorlabel='From']";
  protected final String SL_TO_LABEL                  = "/JDialog[@caption='System Log Report']//JLabel[@caption='To']";
  protected final String SL_TO_BOX                    = "/JDialog[@caption='System Log Report']//JTextField[@priorlabel='To']";
  protected final String MODULES_SEC                  = "/JDialog[@caption='System Log Report']//JPanel[@caption='Modules']";
  protected final String CUR_CON_MODS_BTN             = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='Currently Connected Modules']";
  protected final String ALL_LOG_MODS_BTN             = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='All Logged Modules']";
  protected final String MODS_TABLE_HEADER            = "/JDialog[@caption='System Log Report']//JTableHeader";
  protected final String MDOS_TABLE_BODY              = "/JDialog[@caption='System Log Report']//JViewport";
  protected final String SL_SELECT_ALL_BTN            = "/JDialog[@caption='System Log Report']//JButton[@caption='Select All']";
  protected final String SL_DESELECT_ALL_BTN          = "/JDialog[@caption='System Log Report']//JButton[@caption='Deselect All']";
  protected final String SL_SELECTED_HGHLHT_BTN       = "/JDialog[@caption='System Log Report']//JButton[@caption='Select Highlighted']";
  protected final String SL_DESELECTED_HGHLHT_BTN     = "/JDialog[@caption='System Log Report']//JButton[@caption='Deselect Highlighted']";
  protected final String ERRORS_ONLY_BTN              = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='Errors Only']";
  protected final String ALL_ENTRIES_BTN              = "/JDialog[@caption='System Log Report']//JRadioButton[@caption='All Entries']";
  protected final String SL_GEN_REPORT_BTN            = "/JDialog[@caption='System Log Report']//JButton[@caption='Generate Report File']";
  protected final String SL_PREVIEW_PDF_BTN           = "/JDialog[@caption='System Log Report']//JButton[@caption='Preview PDF']";
  protected final String SL_CLOSE_BTN                 = "/JDialog[@caption='System Log Report']//JButton[@caption='Preview PDF']";

  // Assay Statistics Report
  protected final String ASSAY_STAT_WND               = "/JDialog[@caption='Assay Statistics Report']";
  protected final String LAST_12_MONTHS               = "/JDialog[@caption='Assay Statistics Report']//JRadioButton[@caption='Last 12 Months']";
  protected final String AS_SELECT_BTN                = "/JDialog[@caption='Assay Statistics Report']//JPanel[@caption='Date Range']/JRadioButton[@caption='Select']";
  protected final String AS_FROM_LABEL                = "/JDialog[@caption='Assay Statistics Report']//JLabel[@caption='From']";
  protected final String AS_FROM_BOX                  = "/JDialog[@caption='Assay Statistics Report']//JTextField[@priorlabel='From']";
  protected final String AS_TO_LABEL                  = "/JDialog[@caption='Assay Statistics Report']//JLabel[@caption='To']";
  protected final String AS_TO_BOX                    = "/JDialog[@caption='Assay Statistics Report']//JTextField[@priorlabel='To']";
  protected final String AS_ALL_BOX                   = "/JDialog[@caption='Assay Statistics Report']//JRadioButton[@caption='All']";
  protected final String AS_SELECT_BOX                = "/JDialog[@caption='Assay Statistics Report']//JPanel[@caption='Assay']/JRadioButton[@caption='Select']";
  protected final String AS_TABLE_HEADER              = "/JDialog[@caption='Assay Statistics Report']//JTableHeader";
  protected final String AS_TABLE_BODY                = "/JDialog[@caption='Assay Statistics Report']//JViewport";
  protected final String AS_GEN_REP_BTN               = "/JDialog[@caption='Assay Statistics Report']//JButton[@caption='Generate Report File']";
  protected final String AS_PREVIEW_PDF_BTN           = "/JDialog[@caption='Assay Statistics Report']//JButton[@caption='Preview PDF']";
  protected final String AS_CLOSE_BTN                 = "/JDialog[@caption='Assay Statistics Report']//JButton[@caption='Close']";

  // Installation Qualification Report
  protected final String INSTALL_QUAL_BTN             = "//JMenuItem[@caption='Installation Qualification']";

  // User Administration Menu
  protected final String UA_TABLE_HEADER              = "/JDialog[@caption='User Administration']//JTableHeader";
  protected final String UA_TABLE_BODY                = "/JDialog[@caption='User Administration']//JViewport";
  protected final String UA_ADD_BTN                   = "/JDialog[@caption='User Administration']//JButton[@caption='Add']";
  protected final String UA_REMOVE_BTN                = "/JDialog[@caption='User Administration']//JButton[@caption='Remove']";
  protected final String UA_EDIT_BTN                  = "/JDialog[@caption='User Administration']//JButton[@caption='Edit']";
  protected final String UA_CLOSE_BTN                 = "/JDialog[@caption='User Administration']//JButton[@caption='Close']";

  // User Type Configuration
  protected final String UTC_OK_BTN                   = "/JDialog[@caption='User Type Configuration']//JButton[@caption='OK']";
  protected final String UTC_CANCEL_BTN               = "/JDialog[@caption='User Type Configuration']//JButton[@caption='Cancel']";
  protected final String UTC_RESET_TO_DEF_BTN         = "/JDialog[@caption='User Type Configuration']//JButton[@caption='Reset to Default']";

  // System Configuration Window
  protected final String TABS                         = "/JDialog[@caption='System Configuration']//JTabbedPane";
  protected final String SC_SYSTEM_NAME_LABEL         = "/JDialog[@caption='System Configuration']//JLabel[@caption='System Name']";
  protected final String SC_SYSTEM_NAME_BOX           = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='System Name']";
  protected final String SC_DATE_FORMAT_LABEL         = "/JDialog[@caption='System Configuration']//JLabel[@caption='Date Format']";
  protected final String SC_DATE_FORMAT_BOX           = "/JDialog[@caption='System Configuration']//JComboBox[@priorlabel='Date Format']";
  protected final String SC_TIME_FORMAT_LABEL         = "/JDialog[@caption='System Configuration']//JLabel[@caption='Time Format']";
  protected final String SC_TIME_FORMAT_BOX           = "/JDialog[@caption='System Configuration']//JComboBox[@priorlabel='Time Format']";
  protected final String SC_USE_PATIENT_ID_CHK        = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Use Patient ID']";
  protected final String SC_PATIENT_ONE_CHK           = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Patient ID']";
  protected final String SC_PATIENT_TWO_CHK           = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Patient ID 2']";
  protected final String SC_PATIENT_NAME_CHK          = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Patient Name']";
  protected final String SC_PATIENT_BAR_CHK           = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Scan Patient ID Barcode']";
  protected final String SC_SAMPLE_BAR_CHK            = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Scan Sample ID Barcode']";
  protected final String SC_CART_BAR_CHK              = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Scan Cartridge Barcode']";
  protected final String SC_NEVER_BTN                 = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Never']";
  protected final String SC_ALWAYS_BTN                = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Always']";
  protected final String SC_START_TEST_LOGIN_BTN      = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Start Test Login Timeout (In Minutes)']";
  protected final String SC_START_TEST_MIN_BOX        = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Require Start Test Login']";
  protected final String SC_AUDIO_AT_END_CHK          = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Audio At End Of Test']";
  protected final String SC_PRINT_TEST_CHK            = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Print Test Report At End of Test']";
  protected final String SC_ASSAY_STATS_CHK           = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Assay Statistics Deletion Reminder']";
  protected final String SC_DATABASE_REMIND_CHK       = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Database Management Reminders']";
  protected final String SC_OK_BTN                    = "/JDialog[@caption='System Configuration']//JButton[@caption='OK']";
  protected final String SC_CANCEL_BTN                = "/JDialog[@caption='System Configuration']//JButton[@caption='Cancel']";
  protected final String SC_MANUAL_BTN                = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Manually']";
  protected final String SC_MANUAL_REMIND_BTN         = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Manually, With Reminder']";
  protected final String SC_MANUAL_REMIND_BOX         = "/JDialog[@caption='System Configuration']//JComboBox";
  protected final String SC_EXPORT_FOLDER_LABEL       = "/JDialog[@caption='System Configuration']//JLabel[@caption='Export Folder']";
  protected final String SC_EXPORT_FOLDER_BOX         = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Export Folder']";
  protected final String SC_REPORT_FOLDER_LABEL       = "/JDialog[@caption='System Configuration']//JLabel[@caption='Report Folder']";
  protected final String SC_REPORT_FOLDER_BOX         = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Report Folder']";
  protected final String SC_BACKUP_FOLDER_LABEL       = "/JDialog[@caption='System Configuration']//JLabel[@caption='Backup Folder']";
  protected final String SC_BACKUP_FOLDER_BOX         = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Backup Folder']";
  protected final String SC_DB_LOG_FOLDER_LABEL       = "/JDialog[@caption='System Configuration']//JLabel[@caption='DB Log Folder']";
  protected final String SC_DB_LOG_FOLDER_BOX         = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='DB Log Folder']";
  protected final String SC_REC_USER_LOG_CHK          = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Require User Login in Xpress Software']";
  protected final String SC_GRAPH_KEYBOARD_CHK        = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Use Graphical Keyboard']";
  protected final String SC_HOST_COMM_CHK             = "/JDialog[@caption='System Configuration']//JPanel[@className='yf']";
  protected final String SC_HOST_ID_LABEL             = "/JDialog[@caption='System Configuration']//JLabel[@caption='Host ID']";
  protected final String SC_HOST_ID_BOX               = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Host ID']";
  protected final String SC_AUTO_HOST_CHK             = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Automatic Host Query After Sample ID Scan']";
  protected final String SC_AUTO_TEST_CHK             = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Automatic Test Order Download']";
  protected final String SC_AUTO_RESULT_CHK           = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Automatic Result Upload']";
  protected final String SC_INSTRUM_SPECI_CHK         = "/JDialog[@caption='System Configuration']//JCheckBox[@caption='Use Instrument Specimen ID']";
  protected final String SC_PROTOCOL_LABEL            = "/JDialog[@caption='System Configuration']//JLabel[@caption='Protocol']";
  protected final String SC_HL7_BTN                   = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='HL7']";
  protected final String SC_ASTM_BTN                  = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='ASTM']";
  protected final String SC_RUN_HOST_LABEL            = "/JDialog[@caption='System Configuration']//JLabel[@caption='Run Host As']";
  protected final String SC_SERVER_BTN                = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Server']";
  protected final String SC_CLIENT_BTN                = "/JDialog[@caption='System Configuration']//JRadioButton[@caption='Client']";
  protected final String SC_SERVER_ADDRESS_LABEL      = "/JDialog[@caption='System Configuration']//JLabel[@caption='Server IP Address']";
  protected final String SC_SERVER_IP_BOX             = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Server IP Address']";
  protected final String SC_PORT_LABEL                = "/JDialog[@caption='System Configuration']//JLabel[@caption='Port #']";
  protected final String SC_PORT_BOX                  = "/JDialog[@caption='System Configuration']//JTextField[@priorlabel='Port #']";
  protected final String SC_DELETE_CANCELLED_BTN      = "/JDialog[@caption='System Configuration']//JButton[@caption='Delete Cancelled Orders']";
  protected final String SC_EXPIRE_RESULTS_BTN        = "/JDialog[@caption='System Configuration']//JButton[@caption='Expire Results']";
  protected final String SC_RESET_BUFFER_BTN          = "/JDialog[@caption='System Configuration']//JButton[@caption='Reset Communication Buffer']";

  // Module Reporters
  protected final String MR_MODULE_LABEL              = "/JDialog[@caption='Module Reporters']//JLabel[@caption='Module']";

  // About GeneXpert Dx System Window
  protected final String AB_LICENSE_BTN               = "/JDialog[@caption='About GeneXpert® Dx System']//JButton[@caption='License'";
  protected final String AB_CLOSE_BTN                 = "/JDialog[@caption='About GeneXpert® Dx System']//JButton[@caption='Close']";

  // Scan Patient ID Barcode
  protected final String SCAN_PID_WND                 = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Patient ID Barcode']";
  protected final String PID_DESCRIPTION              = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Patient ID Barcode']//JLabel[@caption='Please scan patient ID barcode']";
  protected final String PID_MANUEL_ENTRY_BTN         = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Patient ID Barcode']//JButton[@caption='Manual Entry']";
  protected final String PID_SCAN_CANCEL_BTN          = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Patient ID Barcode']//JButton[@caption='Cancel']";

  // Scan Sample ID Barcode
  protected final String SCAN_SID_WND                 = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Sample ID Barcode']";
  protected final String SID_DESCRIPTION              = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Sample ID Barcode']//JLabel[@caption='Please scan sample ID barcode']";
  protected final String SID_MANUEL_ENTRY_BTN         = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Sample ID Barcode']//JButton[@caption='Manual Entry']";
  protected final String SID_CANCEL_BTN               = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Sample ID Barcode']//JButton[@caption='Cancel']";

  // Scan Cartridge Barcode
  protected final String SCAN_CAR_WND                 = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Cartridge Barcode']";
  protected final String CAR_DESCRIPTION              = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Cartridge Barcode']//JLabel[@caption='Please scan cartridge barcode']";
  protected final String CAR_MANUEL_ENTRY_BTN         = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Cartridge Barcode']//JButton[@caption='Manual Entry']";
  protected final String CAR_CANCEL_BTN               = "/JDialog[@caption='Create Test']/JDialog[@caption='Scan Cartridge Barcode']//JButton[@caption='Cancel']";

  // Create Test Menu
  protected final String CREATE_TEST_WIN              = "/JDialog[@caption='Create Test']";
  protected final String PATIENT_ID_LABEL             = "/JDialog[@caption='Create Test']//JLabel[@caption='Patient ID']";
  protected final String PATIENT_ID_BOX               = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Patient ID']";
  protected final String SAMPLE_ID_LABEL              = "/JDialog[@caption='Create Test']//JLabel[@caption='Sample ID']";
  protected final String SAMPLE_ID_BOX                = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Sample ID']";
  protected final String CT_NAME_LABEL                = "/JDialog[@caption='Create Test']//JLabel[@caption='Name']";
  protected final String CT_VERSION_LABEL             = "/JDialog[@caption='Create Test']//JLabel[@caption='Version']";
  protected final String CT_SELECT_ASSAY_LABEL        = "/JDialog[@caption='Create Test']//JLabel[@caption='Select Assay']";
  protected final String CT_SELECT_ASSAY_BOX          = "/JDialog[@caption='Create Test']//JComboBox[@priorlabel='Name']";
  protected final String CT_SELECT_MODULE_LABEL       = "/JDialog[@caption='Create Test']//JLabel[@caption='Select Module']";
  protected final String CT_SELECT_MODULE_BOX         = "/JDialog[@caption='Create Test']//JComboBox[@priorlabel='Select Module']";
  protected final String CT_REAGENT_LOTID_LABEL       = "/JDialog[@caption='Create Test']//JLabel[@caption='Reagent Lot ID']";
  protected final String CT_REAGENT_LOTID_BOX         = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Reagent Lot ID']";
  protected final String CT_EXP_LABEL                 = "/JDialog[@caption='Create Test']//JLabel[@caption='Expiration Date']";
  protected final String CT_EXP_BOX                   = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Expiration Date']";
  protected final String CT_CART_SN_LABEL             = "/JDialog[@caption='Create Test']//JLabel[@caption='Cartridge S/N']";
  protected final String CT_CART_SN_BOX               = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Cartridge S/N']";
  protected final String CT_TEST_TYPE_LABEL           = "/JDialog[@caption='Create Test']//JLabel[@caption='Test Type']";
  protected final String CT_TEST_TYPE_BOX             = "/JDialog[@caption='Create Test']//JComboBox[@priorlabel='Test Type']";
  protected final String CT_SAMPLE_TYPE_LABEL         = "/JDialog[@caption='Create Test']//JLabel[@caption='Sample Type']";
  protected final String CT_SAMPLE_TYPE_BOX           = "/JDialog[@caption='Create Test']//JComboBox[@priorlabel='Sample Type']";
  protected final String CT_OT_SAMP_LABEL             = "/JDialog[@caption='Create Test']//JLabel[@caption='Other Sample Type']";
  protected final String CT_OT_SAMP_BOX               = "/JDialog[@caption='Create Test']//JTextField[@priorlabel='Other Sample Type']";
  protected final String CT_NOTES_LABEL               = "/JDialog[@caption='Create Test']//JLabel[@caption='Notes']";
  protected final String CT_NOTES_BOX                 = "/JDialog[@caption='Create Test']//JTextArea[@priorlabel='Notes']";
  protected final String CT_START_TEST_BTN            = "/JDialog[@caption='Create Test']//JButton[@caption='Start Test']";
  protected final String CT_SCAN_CART_BTN             = "/JDialog[@caption='Create Test']//JButton[@caption='Scan Cartridge Barcode']";
  protected final String CT_CANCEL_BTN                = "/JDialog[@caption='Create Test']//JButton[@caption='Cancel']";

  // Stop Test Menu
  protected final String STOP_TEST_WND                = "/JDialog[@caption='Stop Test']";
  protected final String ST_SELECT_RUN_BTN            = "/JDialog[@caption='Stop Test']//JButton[@caption='Select Running']";
  protected final String ST_DESLECTALL_BTN            = "/JDialog[@caption='Stop Test']//JButton[@caption='Deselect All']";
  protected final String ST_STOP_BTN                  = "/JDialog[@caption='Stop Test']//JButton[@caption='Stop']";
  protected final String ST_CANCEL_BTN                = "/JDialog[@caption='Stop Test']//JButton[@caption='Cancel']";

  // View Results Menu
  protected final String VR_PATIENT_ID_LABEL          = "//JLabel[@caption='Patient ID']";
  protected final String VR_PATIENT_ID_BOX            = "//JTextArea[@priorlabel='Patient ID']";
  protected final String VR_SAMPLE_ID_LABEL           = "//JLabel[@caption='Sample ID']";
  protected final String VR_SAMPLE_ID_BOX             = "//JTextArea[@priorlabel='Sample ID']";
  protected final String VR_ASSAY_LABEL               = "//JLabel[@caption='Assay']";
  protected final String VR_ASSAY_BOX                 = "//JTextField[@priorlabel='Assay']";
  protected final String VR_VERSION_LABEL             = "//JLabel[@caption='Version']";
  protected final String VR_VERSION_BOX               = "//JTextField[@priorlabel='Version']";
  protected final String VR_RESULT_LABEL              = "//JLabel[@caption='Result']";
  protected final String VR_RESULT_BOX                = "//JEditorPane[@priorlabel='Result']";
  protected final String VR_SAMP_TYPE_LABEL           = "//JLabel[@caption='Sample Type']";
  protected final String VR_SAMP_TYPE_BOX             = "//JPanel[@className='zr$b']//JPanel/JPanel";
  protected final String VR_CARTRIDGE_LABEL           = "//JLabel[@caption='Cartridge']";
  protected final String VR_CARTRIDGE_BOX             = "//JComboBox[@priorlabel='Cartridge']";
  protected final String VR_OTH_SAMP_LABEL            = "//JLabel[@caption='Other Sample Type']";
  protected final String VR_OTH_SAMP_BOX              = "//JTextField[@priorlabel='Other Sample Type']";
  protected final String VR_USER_LABEL                = "//JLabel[@caption='User']";
  protected final String VR_USER_BOX                  = "//JTextField[@priorlabel='User']";
  protected final String VR_NOTES_LABEL               = "//JLabel[@caption='Notes']";
  protected final String VR_NOTES_BOX                 = "//JTextArea[@priorlabel='Notes']";
  protected final String VR_START_TIME_LABEL          = "//JLabel[@caption='Start Time']";
  protected final String VR_START_TIME_BOX            = "//JTextField[@priorlabel='Start Time']";
  protected final String VR_END_TIME_LABEL            = "//JLabel[@caption='End Time']";
  protected final String VR_END_TIME_BOX              = "//JTextField[@priorlabel='End Time']";
  protected final String VR_STATUS_LABEL              = "//JLabel[@caption='Status']";
  protected final String VR_STATUS_BOX                = locator("//JTextField[@priorlabel='Status']");
  protected final String VR_VIEWS_LIST                = "//JComponent[@className='ViewSelectionPanel']//JList";
  protected final String VR_VIEWS2_LIST               = "//JComponent[@className='ViewSelectionPanel'][2]//JList";
  protected final String VR_TABBED_PANE               = "//JTabbedPane";
  protected final String VR_ASSAY_NAME_LABEL          = "//JLabel[@caption='Assay Name']";
  protected final String VR_ERROR_STATUS_TXT          = "//JTextField[@priorlabel='Error Status']";
  protected final String VR_ANALYTE_RTABLE            = "//JTable";
  protected final String VR_TEST_RESULT_BOX           = "//JTextArea[@priorlabel='Test Result']";
  protected final String VR_TEST_RESULT_BOX2          = "//JPanel[@className='ResultReporterPanel']//JTextArea";
  protected final String VR_WND                       = "//JPanel[@className='MainPanel']/JPanel[2]";
  protected final String VR_LEFT_PANEL                = locator("//JPanel[@className='ResultOverviewPanel$b']/JPanel");
  protected final String VR_SUPPORT_TAB               = locator("//JPanel[@className='JFlowPanel']"); 
  
  // General Buttons
  protected final String SAVE_CHANGES_BTN             = "//JButton[@caption='Save Changes']";
  protected final String EXPORT_BTN                   = "//JButton[@caption='Export']";
  protected final String REPORT_BTN                   = "//JButton[@caption='Report']";
  protected final String SELECT_GRAPHS_BTN            = "//JButton[@caption='Select Graphs']";
  protected final String VIEW_TEST_BTN                = "//JButton[@caption='View Test']";
  protected final String NEW_BTN                      = "//JButton[@caption='New']";
  protected final String DELETE_BTN                   = "//JButton[@caption='Delete']";
  protected final String DUPLICATE_BTN                = "//JButton[@caption='Duplicate']";
  protected final String RENAME_BTN                   = "//JButton[@caption='Rename']";
  protected final String SAVE_BTN                     = "//JButton[@caption='Save']";
  protected final String MOVE_TO_TOP_BTN              = "//JButton[@caption='Move To Top']";
  protected final String CONVERT_BTN                  = "//JButton[@caption='Convert']";
  protected final String LOT_BTN                      = "//JButton[@caption='Lot']";
  protected final String IMPORT_BTN                   = "//JButton[@caption='Import']";
  protected final String MODULE_TABLE                 = "//JTable[@className='qz']";

  // Define Assays Window
  protected final String DA_ASSAY_LABEL               = "//JLabel[@caption='Assay']";
  protected final String DA_TABLE_HEADER              = "//JTableHeader";
  protected final String DA_TABLE_BODY                = "//JViewport";

  // Define Graphs Window
  protected final String GRAPH_NAME_LABEL             = "//JLabel[@caption='Graph Name']";
  protected final String GRAPH_NAME_BOX               = "//JTextField[@priorlabel='Graph Name']";
  protected final String GRAPH_TYPE_LABEL             = "//JLabel[@caption='Graph Type']";
  protected final String GRAPH_TYPE_BOX               = "//JComboBox[@priorlabel='Graph Type']";
  protected final String GRAPH_TYPE_CHK               = "//JCheckBox[@caption='Automatically added to new tests']";
  protected final String PRIMARY_CURVE_CHK            = "//JCheckBox[@caption='Primary Curve']";
  protected final String RAW_DATA_CHK                 = "//JCheckBox[@caption='Raw Data']";
  protected final String NORMALIZED_CHK               = "//JCheckBox[@caption='Normalized']";
  protected final String CROSSTALK_CHK                = "//JCheckBox[@caption='Crosstalk']";
  protected final String DERIVATIVE_CHK               = "//JCheckBox[@caption='2nd Derivative']";
  protected final String FITTED_CHK                   = "//JCheckBox[@caption='Fitted Curve']";
  protected final String THRESHOLD_CHK                = "//JCheckBox[@caption='Threshold']";
  protected final String FLU_VS_CYC_CHK               = "//JRadioButton[@caption='Fluorescence vs Cycle']";
  protected final String LOGFLU_VS_CYC_CHK            = "//JRadioButton[@caption='Log Fluorescence vs Cycle']";
  protected final String NORFLU_VS_CYC_CHK            = "//JRadioButton[@caption='Normalized Fluorescence vs Cycle']";
  protected final String SAMP_TEMP_CHK                = "//JCheckBox[@caption='Sample Temperature']";
  protected final String HEAT_TEMP_CHK                = "//JCheckBox[@caption='Heater Temperature']";
  protected final String TAR_TEMP_CHK                 = "//JCheckBox[@caption='Target Temperature']";
  protected final String ULTRA_HORN_AMP_CHK           = "//JCheckBox[@caption='Ultrasonic Horn Amplitude']";
  protected final String ULTRA_DUTY_CYC_CHK           = "//JCheckBox[@caption='Ultrasonic Duty Cycle']";
  protected final String ASSAY_PRESS_CHK              = "//JCheckBox[@caption='Assay Pressure Threshold']";
  protected final String CART_PRESS_CHK               = "//JCheckBox[@caption='Cartridge Pressure Threshold']";
  protected final String MELT_RAW_CHK                 = "//JCheckBox[@caption='Melt Raw']";
  protected final String MELT_NORM_CHK                = "//JCheckBox[@caption='Melt Normalized']";
  protected final String MELT_CROSS_CHK               = "//JCheckBox[@caption='Melt Crosstalk']";
  protected final String MELT_DERIV_CHK               = "//JCheckBox[@caption='Melt 1st Derivative']";
  protected final String MELT_BASE_CHK                = "//JCheckBox[@caption='Melt Baseline']";
  protected final String MELT_DELTA_CHK               = "//JCheckBox[@caption='Melt Delta']";
  protected final String MELT_TEMP_CHK                = "//JCheckBox[@caption='Melt Temperature(s)']";

  // Advanced Setup
  protected final String ADVANCED_SETUP_TAB           = locator("/JMenu[@caption='Advanced Setup']");

  // View Test Menu
  protected final String TEST_JTABLE                  = locator(
      "/JDialog[@caption='Select Test To Be Viewed']//JTable[@className='TestPanel$2']");
  protected final String TEST_OK_BTN                  = locator(
      "/JDialog[@caption='Select Test To Be Viewed']//JButton[@caption='OK']");
  protected final String TEST_CANCEL_BTN              = locator(
      "/JDialog[@caption='Select Test To Be Viewed']//JButton[@caption='Cancel']");

  // Test Archive Reminder
  protected final String TA_WND                       = locator("/JDialog[@caption='Test Archive Reminder']"); 
  protected final String TA_NO_BTN                    = locator(
      "/JDialog[@caption='Test Archive Reminder']//JButton[@caption='No']");

}
