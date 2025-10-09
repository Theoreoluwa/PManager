package com.theoreoluwa.property.propertymanagement.Controller;

import com.theoreoluwa.property.propertymanagement.DTO.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/calculator")//class level mapping of url to class
public class CalculatorController {
    @GetMapping("/add/{c}")//method level mapping of controller to method
    public Double add(@RequestParam("a") Double a, @RequestParam("b") Double b, @PathVariable("c") Double c) {
        return a + b;
    }

    @GetMapping("/sub/{a}/{b}")//mapping values of url to java variable by path variable method
    public Double subtract(@PathVariable("a") Double a, @PathVariable("b") Double b) {
        Double result = null;
        if (a > b) {
            result = a - b;
        }else {
            result= b-a;
        }
        return result;
    }

    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2()
                * calculatorDTO.getNum3() * calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}
