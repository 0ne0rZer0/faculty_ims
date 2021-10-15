package sample;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PDF extends Main {

    public static void pdfConverter(int RegisterNo,String file){

        //String file= "data.pdf";
        Document document = new Document(PageSize.A4,36f,36f,18f,144f);
        jdbcClass.getConnection();
        Font heading1= new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.BLACK);
        Font heading2= new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL , BaseColor.BLACK);

        Font naming1= new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL,BaseColor.BLACK);
        Font naming2= new Font(Font.FontFamily.HELVETICA,11,Font.BOLD,BaseColor.BLACK);
        Font naming3= new Font(Font.FontFamily.HELVETICA,9,Font.UNDEFINED,BaseColor.BLACK);


        Font font = new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL , BaseColor.WHITE);
        Font personal1= new Font(Font.FontFamily.HELVETICA,8,Font.BOLD , BaseColor.BLACK);
        Font personal2= new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL , BaseColor.BLACK);

        Font heading = new Font(Font.FontFamily.HELVETICA,10,Font.BOLD,BaseColor.WHITE);
        Font tableLabel= new Font(Font.FontFamily.HELVETICA,7,Font.BOLD,BaseColor.BLACK);
        Font tableValue= new Font(Font.FontFamily.HELVETICA,7,Font.NORMAL,BaseColor.BLACK);

        String personalLabel [] ={"Current Address","Gender","Date of Birth","Mother's Name","Category","Religion","Caste","Phone Number","Email Id"};
        String personalValue [] ={"Address","Gender","DOB","MotherName","Category","Religion","Caste","PhoneNo","Email"};

        String educationalLabel [] ={"Degree","Specialization","University Name","Class","Percentage/CGPA","Month and Year of Passing","Completion Status"};
        String educationalValue [] ={"Degree","Specialization","UniversityName","Class","Percentage","MonthYear","CompletionStatus"};

        String educationalPhdLabel [] ={"Domain","Specialization","University Name","Month and Year of Admission","Month and Year of Completion","Completion Status","Name of Research Center"};
        String educationalPhdValue [] ={"Domain","Specialization","UniversityName","YearAdmission","YearCompletion","Status","NameResearch"};

        String publicationLabel [] ={"Authors Name","Publication Type","Title Of Paper","Name Of Journal","Name Of Publisher","Month and Year","Academic Year","Venue","Date From","Date To","Name Of Funding Agency","Funds Received"};
        String publicationValue [] ={"AuthorsName","PublicationType","TitleOfPaper","NameOfJournalConference","NameOfPublisherOrganizer","MonthAndYearOfJournalPublication","AcademicYear","Venue","FromDateofConferencePublication","ToDateofConferencePublication","NameOfFundingAgency","FundsRecieved"};

        String attendedLabel [] ={"Title Of Programme","Type Of Programme","Level Of Programme","Organizer Name","Name of Approving Agency","Venue","Date From","Date To","Duration","Name Of Funding Agency","Funds Received","Academic Year"};
        String attendedValue [] ={"TitleOfProgramme","TypeOfProgramme","LevelOfProgramme","OrganizerName","NameofApprovingAgency","Venue","DateFrom","DateTo","Duration","NameOfFundingAgency","FundsReceived","AcademicYear"};

        String organizedLabel [] ={"Title Of Programme","Type Of Programme","Level Of Programme","Name Of Resource Person","Contact Number","Email Id","Date From","Date To","Duration","Number Of Participants","Target Audience","Academic Year"};
        String organizedValue [] ={"TitleOfProgramme","TypeOfProgramme","LevelOfProgramme","NameOfResourcePerson","ContactNumber","EmailId","DateFrom","DateTo","Duration","NoOfParticipants","TargetAudience","AcademicYear"};

        String fundedLabel [] ={"Role","Collaboration With","Title Of Research Project","Sponsoring Agency","Sanctioned Amount","Received Amount","Utilized Amount","Outcome","Status","Date From","Date To","Academic Year"};
        String fundedValue [] ={"Role","InCollaborationWith","TitleofResearchProduct","SponsoringAgency","SanctionedAmount","ReceivedAmount","UtilizedAmount","Outcome","Status","FromDate","ToDate","AcedemicYear"};

        String guestLabel [] ={"Topics","Name Of Resource Person","Designation","Organization","Mobile No.","Email Id","Target Audience","No. Of Participants","Remuneration","Date Of Conduction","Academic Year"};
        String guestValue [] ={"Topics","NameOfResourcePerson","DesignationOfResourcePerson","ResourcePersonOrganization","ResourcePersonMobileNo","ResourcePersonEmailId","TargetAudience","NumberOfParticipants","Remuneration","DateOfConduction","AcademicYear"};

        String interactionLabel [] ={"Role Of Faculty","Particulars","Venue","Program Date","Target Audience","No. Of Participants","No. Of Days","Academic Year"};
        String interactionValue [] ={"RoleOfFaculty","Particulars","Venue","ProgramDate","TargetAudiance","NoofParticipants","NoofDays","AcademicYear"};

        String stesLabel1 [] ={"Employee No","Date of Appointment of Current Designation","SPPU Current Approval Category","SPPU Date Of Approval Of Current Designation"};
        String stesValue1 [] ={"EmployeeNo","DateAppointmentCurrentDesignation","SPPUCurrentApprovalCategory","SPPUDateOfApprovalOfCurrentDesignation"};
        String stesLabel2 [] ={"Type of Appointment","STES Appointment Order Reference No","SPPU Approval Reference No","Previous Appointment"};
        String stesValue2 [] ={"TypesOfAppointment","AppointmentOrderReferenceNumber","SPPUApprovalReferenceNumber","PreviousAppointment"};

        String post=null;
        //For Blue Line
        LineSeparator line = new LineSeparator(1,100,BaseColor.BLUE, Element.ALIGN_BASELINE,-4);;
        Paragraph paragraphLine;
        Chunk chunkLine;

        Chunk chunk1,chunk2,chunk3,chunk4,chunk5,chunk6,chunk7;

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try{

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            Image image= Image.getInstance("src/res/sinhgad-technical-education.jpg");
            image.setAlignment(Image.ALIGN_LEFT | Image.TEXTWRAP);
            image.scaleToFit(48,72);
            document.add(image);

            Paragraph head1= new Paragraph("Sinhgad Technical Education Society's",heading1);
            head1.setAlignment(Element.ALIGN_CENTER);
            Paragraph head2= new Paragraph("Sinhgad College of Engineering\n" +
                    "Vadgaon (Budruk) Off. Sinhgad Road,S.No. 44/1,",heading2);
            head2.setAlignment(Element.ALIGN_CENTER);
            document.add(head1);
            document.add(head2);


            //Personal table

            String personalQuery="select * from sppu where RegistrationNo='"+RegisterNo+"' and ApprovalCategory='"+"PG"+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(personalQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()){

                    post = resultSet.getString("CurrentDesignation");
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            personalQuery="select * from personal where RegistrationNo='"+RegisterNo+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(personalQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()){


                    Paragraph name = new Paragraph();
                    name.add(new Paragraph("Prof " + resultSet.getString("FirstName") + " " + resultSet.getString("MiddleName") +" " + resultSet.getString("LastName"), naming1));
                    name.add(new Paragraph(post, naming2));
                    name.add(new Paragraph(resultSet.getString("Email"), naming3));
                    name.add(new Paragraph(resultSet.getString("PhoneNo"), naming3));
                    name.add(Chunk.NEWLINE);
                    document.add(name);

                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Personal Details",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);


                    for(int i=0 ;i<9 ;i++) {
                        Paragraph paragraph1 = new Paragraph();
                        chunk1 = new Chunk(personalLabel[i], personal1);
                        chunk2 = new Chunk(new VerticalPositionMark(), 160, true);
                        chunk3 = new Chunk(resultSet.getString(personalValue[i]), personal2);

                        paragraph1.add(chunk1);
                        paragraph1.add(chunk2);
                        paragraph1.add(chunk3);
                        document.add(paragraph1);
                    }

                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            personalQuery="select * from stes where RegistrationNo='"+RegisterNo+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(personalQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()){

                    Paragraph paragraph1 = new Paragraph();
                    chunk1 = new Chunk("Date of Joining SCOE", personal1);
                    chunk2 = new Chunk(new VerticalPositionMark(), 160, true);
                    chunk3 = new Chunk(resultSet.getString("STESJoiningDate"), personal2);
                    paragraph1.add(chunk1);
                    paragraph1.add(chunk2);
                    paragraph1.add(chunk3);
                    document.add(paragraph1);
                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            personalQuery="select * from sppu where RegistrationNo='"+RegisterNo+"' and ApprovalCategory='"+"PG"+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(personalQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()){

                    Paragraph paragraph1 = new Paragraph();
                    chunk1 = new Chunk("Current Designation", personal1);
                    chunk2 = new Chunk(new VerticalPositionMark(), 160, true);
                    chunk3 = new Chunk(resultSet.getString("CurrentDesignation"), personal2);
                    paragraph1.add(chunk1);
                    paragraph1.add(chunk2);
                    paragraph1.add(chunk3);
                    document.add(paragraph1);
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            //SPPU Details
            String stesQuery="select * from stes where RegistrationNo='"+RegisterNo+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(stesQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);


                if(resultSet.next()) {
                    document.add(Chunk.NEWLINE);
                    paragraphLine = new Paragraph();
                    chunkLine = new Chunk("SPPU Details", heading);
                    chunkLine.setBackground(BaseColor.BLUE, 2f, 1f, 40f, 6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            stesQuery="select * from stes where RegistrationNo='"+RegisterNo+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(stesQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);

                while (resultSet.next()) {

                    for(int i=0;i<4;i++) {
                        Paragraph paragraph1 = new Paragraph();
                        chunk1 = new Chunk(stesLabel1[i], personal1);
                        chunk2 = new Chunk(new VerticalPositionMark(), 210, true);
                        chunk3 = new Chunk(resultSet.getString(stesValue1[i]), personal2);
                        chunk4 = new Chunk(new VerticalPositionMark(), 280, true);
                        chunk5 = new Chunk(stesLabel2[i], personal1);
                        chunk6 = new Chunk(new VerticalPositionMark(), 450, true);
                        chunk7 = new Chunk(resultSet.getString(stesValue2[i]), personal2);

                        paragraph1.add(chunk1);
                        paragraph1.add(chunk2);
                        paragraph1.add(chunk3);
                        paragraph1.add(chunk4);
                        paragraph1.add(chunk5);
                        paragraph1.add(chunk6);
                        paragraph1.add(chunk7);
                        document.add(paragraph1);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }


            String sppuQuery="select * from sppu where RegistrationNo='"+RegisterNo+"' and ApprovalCategory='"+"PG"+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(sppuQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    Paragraph paragraph1 = new Paragraph();
                    chunk1 = new Chunk("SPPU PG Teacher Approval Date", personal1);
                    chunk2 = new Chunk(new VerticalPositionMark(), 210, true);
                    chunk3 = new Chunk(resultSet.getString("DateOfApproval"), personal2);
                    chunk4 = new Chunk(new VerticalPositionMark(), 280, true);
                    chunk5 = new Chunk("Reference Number", personal1);
                    chunk6 = new Chunk(new VerticalPositionMark(), 450, true);
                    chunk7 = new Chunk(resultSet.getString("ReferenceNo"), personal2);
                    paragraph1.add(chunk1);
                    paragraph1.add(chunk2);
                    paragraph1.add(chunk3);
                    paragraph1.add(chunk4);
                    paragraph1.add(chunk5);
                    paragraph1.add(chunk6);
                    paragraph1.add(chunk7);
                    document.add(paragraph1);

                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            sppuQuery="select * from sppu where RegistrationNo='"+ RegisterNo +"' and ApprovalCategory='"+"PHD"+"'";
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(sppuQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    Paragraph paragraph1 = new Paragraph();
                    chunk1 = new Chunk("SPPU PHD Recognition Approval Date", personal1);
                    chunk2 = new Chunk(new VerticalPositionMark(), 210, true);
                    chunk3 = new Chunk(resultSet.getString("DateOfApproval"), personal2);
                    chunk4 = new Chunk(new VerticalPositionMark(), 280, true);
                    chunk5 = new Chunk("PHD Guide Approval Reference Number", personal1);
                    chunk6 = new Chunk(new VerticalPositionMark(), 450, true);
                    chunk7 = new Chunk(resultSet.getString("ReferenceNo"), personal2);

                    paragraph1.add(chunk1);
                    paragraph1.add(chunk2);
                    paragraph1.add(chunk3);
                    paragraph1.add(chunk4);
                    paragraph1.add(chunk5);
                    paragraph1.add(chunk6);
                    paragraph1.add(chunk7);
                    document.add(paragraph1);

                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            //Educational table
            PdfPTable educationalTable = new PdfPTable(7);
            educationalTable.setWidthPercentage(100);
            PdfPCell cell;

            String educationalUGPGQuery="select *  from educationalugpg where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(educationalUGPGQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if(resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Academic Qualification",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<7;i++) {
                        cell = new PdfPCell(new Phrase(educationalLabel[i], tableLabel));
                        educationalTable.addCell(cell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            educationalUGPGQuery="select *  from educationalugpg where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(educationalUGPGQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<7;i++) {
                        cell = new PdfPCell(new Phrase(resultSet.getString(educationalValue[i]), tableValue));
                        educationalTable.addCell(cell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            document.add(educationalTable);

            PdfPTable educationalPhdTable = new PdfPTable(7);
            educationalPhdTable.setWidthPercentage(100);
            PdfPCell cellPhd;

            String educationalPHDQuery="select *  from educationalphd where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(educationalPHDQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if(resultSet.next()) {

                    document.add(Chunk.NEWLINE);

                    cellPhd = new PdfPCell(new Phrase("PHD",font));
                    cellPhd.setBackgroundColor(BaseColor.BLUE);
                    cellPhd.setColspan(7);
                    educationalPhdTable.addCell(cellPhd);

                    for(int i=0;i<7;i++) {
                        cellPhd = new PdfPCell(new Phrase(educationalPhdLabel[i], tableLabel));
                        educationalPhdTable.addCell(cellPhd);
                    }

                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            educationalPHDQuery="select *  from educationalphd where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(educationalPHDQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<7;i++) {
                        cellPhd = new PdfPCell(new Phrase(resultSet.getString(educationalPhdValue[i]), tableValue));
                        educationalPhdTable.addCell(cellPhd);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            document.add(educationalPhdTable);

            //Publication table
            PdfPTable publicationTable = new PdfPTable(12);
            publicationTable.setWidthPercentage(100);
            PdfPCell publicationCell;

            String publicationQuery="select *  from publication where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(publicationQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Publication",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<12;i++) {
                        publicationCell = new PdfPCell(new Phrase(publicationLabel[i], tableLabel));
                        publicationTable.addCell(publicationCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            publicationQuery="select *  from publication where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(publicationQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<12;i++) {
                        publicationCell = new PdfPCell(new Phrase(resultSet.getString(publicationValue[i]), tableValue));
                        publicationTable.addCell(publicationCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            document.add(publicationTable);

            //Attended table
            PdfPTable attendedTable = new PdfPTable(12);
            attendedTable.setWidthPercentage(100);
            PdfPCell attendedCell;

            String attendedQuery="select *  from attended where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(attendedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Attended Seminar / Workshop",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<12;i++) {
                        attendedCell = new PdfPCell(new Phrase(attendedLabel[i], tableLabel));
                        attendedTable.addCell(attendedCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            attendedQuery="select *  from attended where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(attendedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<12;i++) {
                        attendedCell = new PdfPCell(new Phrase(resultSet.getString(attendedValue[i]), tableValue));
                        attendedTable.addCell(attendedCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            document.add(attendedTable);

            //Organized Seminar / Workshop table
            PdfPTable organizedTable = new PdfPTable(12);
            organizedTable.setWidthPercentage(100);
            PdfPCell organizedCell;

            String organizedQuery="select *  from organized where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(organizedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Organized Seminar / Workshop",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<12;i++) {
                        organizedCell = new PdfPCell(new Phrase(organizedLabel[i], tableLabel));
                        organizedTable.addCell(organizedCell);
                    }

                }
            }catch (SQLException e){

                e.printStackTrace();
            }


            organizedQuery="select *  from organized where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(organizedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<12;i++) {
                        organizedCell = new PdfPCell(new Phrase(resultSet.getString(organizedValue[i]), tableValue));
                        organizedTable.addCell(organizedCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            document.add(organizedTable);

            //fundedResearchProduct table
            PdfPTable fundedTable = new PdfPTable(12);
            fundedTable.setWidthPercentage(100);
            PdfPCell fundedCell;

            String fundedQuery="select *  from fundedresearchproduct where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(fundedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Funded Research",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<12;i++) {
                        fundedCell = new PdfPCell(new Phrase(fundedLabel[i], tableLabel));
                        fundedTable.addCell(fundedCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }


            fundedQuery="select *  from fundedresearchproduct where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(fundedQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<12;i++) {
                        fundedCell = new PdfPCell(new Phrase(resultSet.getString(fundedValue[i]), tableValue));
                        fundedTable.addCell(fundedCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            document.add(fundedTable);

            //Guest Lecture Arranged
            PdfPTable guestTable = new PdfPTable(11);
            guestTable.setWidthPercentage(100);
            PdfPCell guestCell;

            String guestQuery="select *  from guestlecture where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(guestQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Guest Lecture Arranged",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<11;i++) {
                        guestCell = new PdfPCell(new Phrase(guestLabel[i], tableLabel));
                        guestTable.addCell(guestCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }


            guestQuery="select *  from guestlecture where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(guestQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<11;i++) {
                        guestCell = new PdfPCell(new Phrase(resultSet.getString(guestValue[i]), tableValue));
                        guestTable.addCell(guestCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            document.add(guestTable);

            //Interaction with the outside World
            PdfPTable interactionTable = new PdfPTable(8);
            interactionTable.setWidthPercentage(100);
            PdfPCell interactionCell;

            String interactionQuery="select *  from interaction where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(interactionQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                if (resultSet.next()) {

                    document.add(Chunk.NEWLINE);
                    paragraphLine=new Paragraph();
                    chunkLine = new Chunk("Interaction with the outside World",heading);
                    chunkLine.setBackground(BaseColor.BLUE,2f,1f,40f,6f);
                    paragraphLine.add(chunkLine);
                    paragraphLine.add(line);
                    document.add(paragraphLine);

                    document.add(Chunk.NEWLINE);

                    for(int i=0;i<8;i++) {
                        interactionCell = new PdfPCell(new Phrase(interactionLabel[i], tableLabel));
                        interactionTable.addCell(interactionCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }

            interactionQuery="select *  from interaction where RegistrationNo='"+RegisterNo+"'" ;
            try {
                preparedStatement = jdbcClass.connection.prepareStatement(interactionQuery);
                resultSet = jdbcClass.fireQuery(preparedStatement);
                while (resultSet.next()) {

                    for(int i=0;i<8;i++) {
                        interactionCell = new PdfPCell(new Phrase(resultSet.getString(interactionValue[i]), tableValue));
                        interactionTable.addCell(interactionCell);
                    }
                }
            }catch (SQLException e){

                e.printStackTrace();
            }
            document.add(interactionTable);

            document.close();

        }catch (Exception e){

            e.printStackTrace();
        }
    }

}
