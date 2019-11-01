package cxengage.facebook.engine;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;
import cxengage.tools.email.SendEmail;

public abstract class Engine extends Thread {
	private transient Logger logger = Logger.getLogger(Engine.class);

	protected boolean checkHistory;

	public abstract Tenant getTenant();

	public abstract void setTenant(Tenant tenant);

	public abstract int getRate();

	public abstract void setRate(String rate) throws Exception;

	public abstract boolean isActive();

	public abstract void setActive(boolean active);

	public abstract RouterLog getRouterLog();

	public abstract EngineStatus getStatus();

	public abstract void setStatus(EngineStatus status);

	public abstract void performEngineJob() throws Exception;

	public boolean isCheckHistory() {
		return checkHistory;
	}

	public void setCheckHistory(boolean checkHistory) {
		this.checkHistory = checkHistory;
	}

	public boolean checkWorkingHours() {
		Tenant tenant = getTenant();
		if (tenant.getStartTime() == null || tenant.getEndTime() == null) {
			logger.warn("No working hours are defined, engine will keep working day and night !!!");
			return true;
		}
		if (tenant.getStartTime().isEmpty() || tenant.getEndTime().isEmpty()) {
			logger.warn("No working hours are defined, engine will keep working day and night !!!");
			return true;
		}
		DateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		;
		try {
			Date startDateAbs = dateFormatter.parse(tenant.getStartTime());
			Date endDateAbs = dateFormatter.parse(tenant.getEndTime());
			Date startDate = new Date();
			Date endDate = new Date();
			Date nowDate = new Date();
			startDate.setHours(startDateAbs.getHours());
			startDate.setMinutes(startDateAbs.getMinutes());
			startDate.setSeconds(startDateAbs.getSeconds());

			endDate.setHours(endDateAbs.getHours());
			endDate.setMinutes(endDateAbs.getMinutes());
			endDate.setSeconds(endDateAbs.getSeconds());
			if (endDate.getHours() < 12 && nowDate.getHours() >= 12) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, 1);
				endDate = cal.getTime();
			}

			if (startDate.getHours() >= 12 && nowDate.getHours() < 12) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, -1);
				startDate = cal.getTime();
			}

			if (nowDate.after(startDate) && nowDate.before(endDate))
				return true;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Start Time is:" + tenant.getStartTime() + " , end time is:" + tenant.getEndTime());
		return false;
	}

	@Override
	public void run() {
		logger.debug("Engine will start now");
		if (getStatus() == EngineStatus.Error) {
			logger.debug("Engine Error");
			setActive(false);
		}
		while (isActive()) {
			try {
				if (checkWorkingHours() || checkHistory) {
					setStatus(EngineStatus.Running);
					performEngineJob();
					logger.debug("We have completed the engine job completely");
				} else {
					setStatus(EngineStatus.Sleep);
					logger.debug("Outside working hours");
				}
			} catch (Exception e) {
				// In case of engine error , we should send notification email
				logger.debug("Engine Error");
				logger.error(e);
				SendEmail.getInstance().sendErrorMail("Engine Error", e.getStackTrace(), e.getMessage(), getTenant());
				setStatus(EngineStatus.Error);
				setActive(false);
				e.printStackTrace();
				return;
			}

			synchronized (this) {
				try {
					if (!checkHistory) {
						setStatus(EngineStatus.Sleep);
						wait(getRate() * 60 * 1000);
					} else {
						setActive(false);
						setStatus(EngineStatus.Stop);
					}
				} catch (InterruptedException e) {
					logger.debug("Session was interrupted and engine will continue");
				}
			}
		}
		logger.debug("Engine will stop now");
		setStatus(EngineStatus.Stop);
	}

}
