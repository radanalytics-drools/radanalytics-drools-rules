package io.radanalytics.tutorial.drools.rules;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import io.radanalytics.tutorial.drools.rule.model.Input;
import io.radanalytics.tutorial.drools.rule.model.Output;

public class RulesTest {

    @Test
    public void testRules() {
        Input i1 = new Input( "Michael" );
        Input i2 = new Input( "Billy" );
        Input i3 = new Input( "Matt" );
        Input i4 = new Input( "Kevin" );
        Input i5 = new Input( "Eddied" );

        KieSession session = KieServices.Factory.get().newKieClasspathContainer().newKieSession( "test-ksession" );
        session.insert( i1 );
        session.insert( i2 );
        session.insert( i3 );
        session.insert( i4 );
        session.insert( i5 );

        int firedCount = session.fireAllRules();

        Output expected = new Output( 5l );
        Output result = (Output) session.getObjects( new ClassObjectFilter( Output.class ) ).stream().findFirst().get();

        assertEquals( result, expected );
        assertEquals( firedCount, 1 );

    }

}
