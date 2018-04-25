package codecheck.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import codecheck.client.RestClient;

public class MainServiceTest {
	@Mock
	RestClient restClient;
	@InjectMocks
	MainService mainService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mainService.init("abc");
	}

	@Test
	public void test_n0に対して1を返却する() {
		assertEquals(mainService.calc(0), 1);
	}
	
	@Test
	public void test_n2に対して2を返却する() {
		assertEquals(mainService.calc(2), 2);
	}
	
	@Test
	public void test_n4に対して再帰的に計算が行われる() {
		when(restClient.askServer("abc", 1)).thenReturn(10);
		when(restClient.askServer("abc", 3)).thenReturn(5);
		
		int actual = mainService.calc(4);
		int expected = 1 + 10 + 2 + 5;
		
		assertEquals(actual, expected);
	}
	
	@Test
	public void test_n30に対してaskServerが15回しかコールされない() {
		when(restClient.askServer(eq("abc"), anyInt())).thenReturn(1);
		
		mainService.calc(30);
		
		// n=1,3,5,...,29 で 1 回ずつ呼ばれ、計 15 回
		verify(restClient, times(15)).askServer(eq("abc"), anyInt());
	}

}
