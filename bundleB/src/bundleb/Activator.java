package bundleb;

import org.foo.IFoo;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator,
		ServiceTrackerCustomizer<IFoo, ServiceReference<IFoo>> {

	private ServiceTracker<IFoo, ServiceReference<IFoo>>	fooTracker;
	public void start(BundleContext bundleContext) throws Exception {
		fooTracker = new ServiceTracker<IFoo, ServiceReference<IFoo>>(
				bundleContext, IFoo.class,
				this);
		fooTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		fooTracker.close();
	}

	@Override
	public ServiceReference<IFoo> addingService(ServiceReference<IFoo> reference) {
		System.out.println("Tracking " + reference);
		return reference;
	}

	@Override
	public void modifiedService(ServiceReference<IFoo> reference,
			ServiceReference<IFoo> service) {
		System.out.println("Modified " + reference);
	}

	@Override
	public void removedService(ServiceReference<IFoo> reference,
			ServiceReference<IFoo> service) {
		System.out.println("Untracking " + reference);
	}
}
