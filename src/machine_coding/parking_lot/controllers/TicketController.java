package machine_coding.parking_lot.controllers;

import machine_coding.parking_lot.dtos.*;
import machine_coding.parking_lot.exceptions.*;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.services.*;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto) {
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        try {
            if (requestDto.getGateId() < 0) {
                throw new InvalidRequestException("Invalid gateId");
            }
            if (requestDto.getVehicleType()==null || requestDto.getVehicleType().equals(" ")) {
                throw new InvalidRequestException("Vehicle type is mandatory");
            }
        } catch (InvalidRequestException e) {
            Response response = new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        Response response=new Response();
        try {
            Ticket ticket= ticketService.generateTicket(requestDto.getGateId(), requestDto.getVehicleNumber(), requestDto.getVehicleType());
            responseDto.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);


        }
        catch (Exception e){
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        responseDto.setResponse(response);
        return  responseDto;
    }
}
