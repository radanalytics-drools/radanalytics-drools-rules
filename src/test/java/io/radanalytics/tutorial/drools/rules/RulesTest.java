package io.radanalytics.tutorial.drools.rules;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.stream.Stream;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;

import io.radanalytics.tutorial.drools.rule.model.Input;

public class RulesTest {

    @Test
    public void testRules() {
        Input michael = new Input( "Michael" );
        Input billy = new Input( "Billy" );
        Input katie = new Input( "Katie" );
        Input matt = new Input( "Matt" );
        Input kevin = new Input( "Kevin" );
        Input eddie = new Input( "Eddie" );
        Input sheila = new Input( "Sheila" );
        Input kelly = new Input( "Kelly" );
        Input lauren = new Input( "Lauren" );

        KieSession session = KieServices.Factory.get().newKieClasspathContainer().newKieSession( "test-ksession" );

        Stream.of( michael, billy, katie, matt, kevin, eddie, sheila, kelly, lauren ).forEach( session::insert );
        int firedCount = session.fireAllRules();

        List<Input> results = (List<Input>) session.getObjects( new ClassObjectFilter( Input.class ) ).stream().collect( Collectors.toList() );

        List<Input> valid = results.stream().filter( x -> x.isValid() ).collect( Collectors.toList() );

        assertTrue( valid.contains( michael ) );
        assertTrue( valid.contains( billy ) );
        assertTrue( valid.contains( matt ) );
        assertTrue( valid.contains( kevin ) );
        assertTrue( valid.contains( eddie ) );
        assertFalse( valid.contains( katie ) );
        assertFalse( valid.contains( kelly ) );
        assertFalse( valid.contains( lauren ) );
        assertFalse( valid.contains( sheila ) );

        assertEquals( firedCount, 5 );

    }

}
