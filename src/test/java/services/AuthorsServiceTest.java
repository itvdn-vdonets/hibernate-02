package services;

import org.example.models.Author;
import org.example.repos.AuthorRepository;
import org.example.services.AuthorsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorsServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorsService authorsService;

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenAddAuthor_thenAssertionSucceeds () {
        //Given
        ArgumentCaptor<Author> authorArgumentCaptor = ArgumentCaptor.forClass(Author.class);
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Author expectedAuthor = new Author();
        Integer expectedId = 1;
        expectedAuthor.setId(expectedId);
        expectedAuthor.setName("testName");
        expectedAuthor.setSalary(5000.0);

        //When
        when(authorRepository.addAuthor(any(Author.class))).thenReturn(expectedAuthor);
        when(authorRepository.isAuthorExist(any(Integer.class))).thenReturn(false);
        Author actualAuthor = authorsService.addAuthor(new Author());

        //Then
        assertEquals(expectedId, actualAuthor.getId());
        verify(authorRepository, times(1)).addAuthor(authorArgumentCaptor.capture());
        verify(authorRepository, times(1)).isAuthorExist(integerArgumentCaptor.capture());
    }

    //JUnit, Mockito, TDD
    @Test
    public void whenAddAuthor_thenExceptionFails() {
        //Given
        Author expectedAuthor = null;

        //When
        when(authorRepository.addAuthor(any(Author.class))).thenReturn(null);
    }


}
