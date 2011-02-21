package bundletm;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration< ? >	reg;

	public void start(BundleContext bundleContext) throws Exception {
		BundleContext proxyContext = null;
		for (Bundle bundle : bundleContext.getBundles()) {
			if ("bundleProxy".equals(bundle.getSymbolicName())) {
				proxyContext = bundle.getBundleContext();
				break;
			}
		}
		reg = proxyContext.registerService("org.foo.IFoo",
				new ServiceFactory<Object>() {

					@Override
					public Object getService(Bundle bundle,
							ServiceRegistration<Object> registration) {
						return null;
					}

					@Override
					public void ungetService(Bundle bundle,
							ServiceRegistration<Object> registration,
							Object service) {
						// empty
					}
				}, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		reg.unregister();
	}

}
