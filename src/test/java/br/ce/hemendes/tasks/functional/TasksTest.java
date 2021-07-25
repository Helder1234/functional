package br.ce.hemendes.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		try {
		
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", message);
		
		}finally {			
			// Fechar browser		
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		WebDriver driver = acessarAplicacao();
		try {
		
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			
			// Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", message);
		
		}finally {			
			// Fechar browser		
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		WebDriver driver = acessarAplicacao();
		try {
		
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", message);
		
		}finally {			
			// Fechar browser		
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDataPassada() {
		
		WebDriver driver = acessarAplicacao();
		try {
		
			// Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", message);
		
		}finally {			
			// Fechar browser		
			driver.quit();
		}
	}
}
