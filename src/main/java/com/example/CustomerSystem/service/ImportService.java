package com.example.CustomerSystem.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.CustomerSystem.entity.MstCustomer;
import com.example.CustomerSystem.form.CustomActionMessage;
import com.example.CustomerSystem.repository.CustomerRepository;

@Service
public class ImportService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public void handleImport(MultipartFile file) {

	    // Create a List<Integer> to store the index of inserted rows
	    List<Integer> insertedLines = new ArrayList<>();

	    // Create a List<Integer> to store the index of updated lines
	    List<Integer> updatedLines = new ArrayList<>();
	        try {
	            String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
	            String[] lines = fileContent.split("\n");

	            List<CustomActionMessage> errorMessages = validateData(lines);
	            
	            if (errorMessages.isEmpty()) {
	            	for (int i = 1; i < lines.length; i++) {
		                String line = lines[i];
		                String[] columns = line.split(",");

		                if (columns.length >= 6) {
		                    // Get customerId from the file and remove spaces and ""
		                    String customerIdFromFile = columns[0].replace("\"", "").trim();

		                    // If customerId is empty, perform insert
		                    if (customerIdFromFile.isEmpty()) {
		                        // Save data
		                        MstCustomer customer = createCustomerObjectFromLine(columns);
		                        customerRepository.save(customer);

		                        // Add index of updated lines to inserted rows
		                        insertedLines.add(i + 1);
		                    } else {
		                        MstCustomer customer = new MstCustomer();
		                        // Update data
		                        customer.setCustomerId(Integer.parseInt(customerIdFromFile));
		                        customer = customerRepository.findByCustomerId(customer.getCustomerId());
		                        MstCustomer customerUpdate = createCustomerObjectFromLine(columns);

		                        // get the name, sex, birthday, email values ​​to check if there are any changes
		                        String customerNameFile = customerUpdate.getCustomerName();
		                        String customerSex = customerUpdate.getSex();
		                        String customerBirthDayFile = customerUpdate.getBirthDay();
		                        String customerEmailFile = customerUpdate.getEmail();

		                        // Check if name, sex, birthday, email are different, then update the record
		                        if (!customerNameFile.equals(customer.getCustomerName())
		                                || !customerSex.equals(customer.getSex())
		                                || !customerBirthDayFile.equals(customer.getBirthDay())
		                                || !customerEmailFile.equals(customer.getEmail())) {

		                            customerUpdate.setCustomerId(Integer.parseInt(customerIdFromFile));

		                            // Handle update customerUpdate file
		                            customerRepository.save(customerUpdate);

		                            // Add index of updated lines to updatedLines
		                            updatedLines.add(i + 1);
		                        }
		                    }
		                }
		            }
	            	saveMessageFile(insertedLines, updatedLines, file);
	            } else {
					
					 String filePath = saveErrorFile(errorMessages);
					 System.out.println(filePath);
					 
	            }
	            
	            
	        } catch (IOException e) {
	            // Handle IOException, e.g., log or throw a custom exception
	            e.printStackTrace();
	        }
	}
	
	/**
	 * Save the success message file
	 * 
	 * @param insertedLines		Create a List<Integer> to store the index of inserted rows
	 * @param updatedLines		Create a List<Integer> to store the index of updated lines
	 * @param importForm		Form data import client
	 */
	public void saveMessageFile(List<Integer> insertedLines, List<Integer> updatedLines, MultipartFile file) {
	    Map<String, Object> importResult = new HashMap<>();
	    importResult.put("successMessage", "Customer data have been imported successfully");
	    importResult.put("insertedLines", insertedLines);
	    importResult.put("updatedLines", updatedLines);

	    String importMessageSuccess = buildImportMessage(importResult);
	    String filePath = saveMessageImportFileSuccess(importMessageSuccess, file);
	    
	    System.out.println(filePath);
	    // Handle filePath as needed (e.g., log it or use it in your application)
	    //importForm.setFolderPathSuccess(filePath);
	}

	
	/**
	 * Record errorMessage to file
	 * 
	 * @param errorMessages	List of errors errorMessages
	 * @return	A file has errors
	 */
	public String saveErrorFile(List<CustomActionMessage> errorMessages) {
	    try {
	        // Write file path
	        String baseFolder = "C:\\";
	        String errorsFolder = "ErrorFileImport";
	        String driveName = Paths.get(baseFolder, errorsFolder).toString();

	        // Convert time to "yyyyMMdd" format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String formattedDate = dateFormat.format(new Date());

	        // Create a new filename
	        String fileName = "error_file_" + formattedDate + ".txt";
	        Path filePath = Paths.get(driveName, fileName);

	        // Create the "errors" folder if it doesn't exist
	        Path errorsFolderPath = Paths.get(driveName);
	        if (!Files.exists(errorsFolderPath)) {
	            Files.createDirectories(errorsFolderPath);
	        }

	        // Write error messages to the file
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
	            for (CustomActionMessage errorMessage : errorMessages) {
	                writer.write(errorMessage.getMessage());
	                writer.newLine();
	            }
	        }
	        return filePath.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	
	/**
	 * Write down the success message file recording information
	 * 
	 * @param importMessageSuccess
	 * @return
	 */
	public String saveMessageImportFileSuccess(String importMessageSuccess, MultipartFile file) {
	    try {
	        //write file path
	        String baseFolder ="C:\\";
	        String successFolder = "SuccessFileImport";
	        String driveName = Paths.get(baseFolder, successFolder).toString();
	        
	        // Convert time to "yyyyMMdd" format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String formattedDate = dateFormat.format(new Date());
	        
	        // Create a new filename
	        String fileName = "success_file_" + formattedDate + ".txt";
	        Path filePath = Paths.get(driveName, fileName);
	        
	        // Create the "SuccessFileImport" folder if it doesn't exist
	        Path successFolderPath = Paths.get(driveName);
	        if (!Files.exists(successFolderPath)) {
	            Files.createDirectories(successFolderPath);
	        }
	        
	        // Save the success message along with the file content
	        Files.write(filePath, importMessageSuccess.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

	        return filePath.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	/**
	 * Get 1 column in the file, remove the ""
	 * 
	 * @param columns Checking the column
	 * @return		  Returns 1 column with "" removed
	 */
	private MstCustomer createCustomerObjectFromLine(String[] columns) {
	    // Assuming your MSTCUSTOMER class has a constructor that takes relevant parameters
		MstCustomer customer = new MstCustomer();
		customer.setCustomerName(columns[1].replace("\"", "").trim());
		
		//Handle the case of male or female gender
		String sex = columns[2].replace("\"", "").trim();
		if ("Male".equals(sex)) {
	        customer.setSex("1"); 
	    } else if ("Female".equals(sex)) {
	        customer.setSex("0");
	    } else {
	        customer.setSex(sex);
	    }
		
		customer.setBirthDay(columns[3].replace("\"", "").trim());
		customer.setEmail(columns[4].replace("\"", "").trim());
		customer.setAddress(columns[5].replace("\"", "").trim());

	    return customer;
	}
	
	/**
	 * Validate data from file
	 * 
	 * @param lines 		 Input is CSV file
	 * @return errorMessages Returns a list of errorMessages
	 */
	private List<CustomActionMessage> validateData(String[] lines) {
		List<MstCustomer> listCustomer = customerRepository.findAllActiveCustomers();
		List<CustomActionMessage> errorMessages = new ArrayList<>();
		
	    for (int i = 1; i < lines.length; i++) {
	    	String line = lines[i];
	        String[] columns = line.split(",");
	        
	        if (columns.length >= 6) {
	            String customerIdFromFile = columns[0].replace("\"", "").trim();
	            String customerNameFromFile = columns[1].replace("\"", "").trim();
	            String customerSexFromFile = columns[2].replace("\"", "").trim();
	            String customerBirthDayFromFile = columns[3].replace("\"", "").trim();
	            String customerEmailFromFile = columns[4].replace("\"", "").trim();
	            String customerAddressFromFile = columns[5].replace("\"", "").trim();

	            if (!customerIdFromFile.isEmpty()) {
	                boolean isCustomerExisted = false;
	                for (MstCustomer customer : listCustomer) {
	                    String customerId = String.valueOf(customer.getCustomerId());

	                    // Check the CUSTOMER_ID in the MSTCUSTOMER table
	                    if (customerId.equals(customerIdFromFile) && customer.getDeleteYmd() == null) {
	                        isCustomerExisted = true;
	                        break;
	                    }
	                }
	                if (!isCustomerExisted) {
	                    errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : CUSTOMER_ID={1} is not existed", i + 1, customerIdFromFile)));
	                }
	            }

	            // Validate CUSTOMER_NAME length
	            if (customerNameFromFile.isEmpty()) {
	                errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : CUSTOMER_ID={1} is not existed", i + 1, customerNameFromFile)));
	            } else if (customerNameFromFile.length() > 50) {
	                errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : Value of CSUTOMER_NAME is more than 50 characters", i + 1)));
	            }

                // Validate SEX validity
                if (!"Male".equals(customerSexFromFile) && !"Female".equals(customerSexFromFile)) {
                	errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : SEX={1} is invalid", i + 1, customerSexFromFile)));
                }

                // Validate BIRTHDAY format and validity
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate parsedDate = LocalDate.parse(customerBirthDayFromFile, formatter);

                    if (!isValidDate(parsedDate)) {
                    	errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : BIRTHDAY={1} is invalid", i + 1, customerBirthDayFromFile)));
                    }
                } catch (DateTimeParseException e) {
                	e.printStackTrace();
                }

                // Validate EMAIL format and length
                if (!isValidEmail(customerEmailFromFile)) {
                	errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : EMAIL={1} is invalid", i + 1, customerEmailFromFile)));
                
                } else if (customerEmailFromFile.length() > 40) {
                	errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : Value of EMAIL is more than 40 characters", i + 1)));
                }

                // Validate ADDRESS length
                if (customerAddressFromFile.length() > 256) {
                	errorMessages.add(new CustomActionMessage("", MessageFormat.format("Line {0} : Value of ADDRESS is more than 256 characters", i + 1)));
                }
	        }
	    }
	    return errorMessages;
	}
	
	/**
     * Builds an import message based on the import result.
     *
     * @param importResult The import result containing success message, inserted lines, and updated lines.
     * @return A formatted import message.
     */
	private String buildImportMessage(Map<String, Object> importResult) {
		// Create a StringBuilder to build the message
	    StringBuilder message = new StringBuilder(importResult.get("successMessage").toString());

	    if (importResult.containsKey("insertedLines")) {
	        message.append("\nInserted line(s): ");
	        @SuppressWarnings("unchecked")
			List<Integer> insertedLines = (List<Integer>) importResult.get("insertedLines");
	        for (int i = 0; i < insertedLines.size(); i++) {
	            message.append(insertedLines.get(i));
	            if (i < insertedLines.size() - 1) {
	                message.append(", ");
	            }
	        }
	    }

	    if (importResult.containsKey("updatedLines")) {
	        message.append("\nUpdate line(s): ");
	        @SuppressWarnings("unchecked")
			List<Integer> updatedLines = (List<Integer>) importResult.get("updatedLines");
	        for (int i = 0; i < updatedLines.size(); i++) {
	            message.append(updatedLines.get(i));
	            if (i < updatedLines.size() - 1) {
	                message.append(", ");
	            }
	        }
	    }

	    return message.toString();
	}
	
	/**
	 * Check email address
	 * 
	 * @param email	Email needs checking
	 * @return		Returns whether the Email is in the correct format or not
	 */
	private static boolean isValidEmail(String email) {
	    //Check the basic format of the email
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	    //Check email format by regex matching
	    return email.matches(emailRegex);
	}
	
	/**
	 * Check birthday date
	 * 
	 * @param date The birthday date is checked and transmitted
	 * @return 	   Returns whether the date is correct or not
	 */
	private static boolean isValidDate(LocalDate date) {
	    try {
	    	//Test the format again to check if the date is valid
	        LocalDate.parse(date.toString());
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
}
