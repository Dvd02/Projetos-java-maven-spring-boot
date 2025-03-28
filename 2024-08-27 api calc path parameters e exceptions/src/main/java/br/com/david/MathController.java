package br.com.david;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.david.utils.Calc;
import br.com.david.utils.Parse;

@RestController
public class MathController {

  @RequestMapping(value = "calc/{firstValue}/{operation}/{secondValue}", method = RequestMethod.GET)
  public Double sum(
    @PathVariable(value = "firstValue") String strFirstValue,
    @PathVariable(value = "operation") String operation,
    @PathVariable(value = "secondValue") String strSecondValue
  ) {
    Double firstValue = Parse.parseToDouble(strFirstValue);
    Double secondValue = Parse.parseToDouble(strSecondValue);
    return Calc.calc(operation, firstValue, secondValue);
  }

}
